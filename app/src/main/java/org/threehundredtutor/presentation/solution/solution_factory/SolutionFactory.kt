package org.threehundredtutor.presentation.solution.solution_factory

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType.UNKNOWN
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.html_page.adapter.HtmlPageHeaderItem
import org.threehundredtutor.presentation.html_page.adapter.HtmlPageStartTestUiModel
import org.threehundredtutor.presentation.solution.mapper.toAnswerSelectRightUiModel
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.toAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerInputUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerYourAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.DividerUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.FooterUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ImageUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ResultButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.SupSubUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.TextUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.YoutubeUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.toRightAnswerUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerTitleUiItem
import org.threehundredtutor.ui_common.EMPTY_STRING
import javax.inject.Inject

class SolutionFactory @Inject constructor(
    private val resourceProvider: ResourceProvider
) {
    private var solutionUiItems: MutableList<SolutionUiItem> = mutableListOf()

    fun createSolution(
        testSolutionGeneralModel: TestSolutionGeneralModel,
        staticUrl: String
    ): List<SolutionUiItem> =
        testSolutionGeneralModel.testSolutionModel.flatMap { testSolutionUnionModel ->
            createSolutionItems(
                questionModel = testSolutionUnionModel.questionModel,
                answerModel = testSolutionUnionModel.answerModel,
                isQuestionLikedByStudent = testSolutionUnionModel.isQuestionLikedByStudent,
                staticUrl = staticUrl
            )
        }

    fun createHtmlPage(
        directoryModel: DirectoryModel,
        htmlString: String,
        staticUrl: String
    ): List<SolutionUiItem> {
        return buildList {
            add(HtmlPageHeaderItem)
            addAll(createQuestionWithHtml(htmlString = htmlString, staticUrl = staticUrl))
            add(FooterUiItem)
            add(DividerUiItem(DividerType.BACKGROUND))
            add(DividerUiItem(DividerType.BACKGROUND))
            if (directoryModel.hasTest()) {
                add(
                    HtmlPageStartTestUiModel(
                        firstCount = directoryModel.getFirstPathCount().toString(),
                        secondCount = directoryModel.getSecondPathCount().toString(),
                        fullCount = directoryModel.getFullTestCount().toString(),
                        firstVisible = directoryModel.hasFirstPath(),
                        secondVisible = directoryModel.hasSecondPath(),
                        fullVisible = directoryModel.hasFullTest()
                    )
                )
                add(DividerUiItem(DividerType.BACKGROUND))
            }
        }
    }

    private fun createSolutionItems(
        questionModel: QuestionModel,
        answerModel: AnswerModel,
        isQuestionLikedByStudent: Boolean,
        countDividerBottomQuestion: Int = COUNT_DIVIDER_BOTTOM_QUESTION,
        staticUrl: String
    ): List<SolutionUiItem> {
        solutionUiItems.clear()
        solutionUiItems.add(
            HeaderUiItem(
                questionId = questionModel.questionId,
                questionNumber = resourceProvider.string(
                    UiCoreStrings.question_number,
                    questionModel.questionNumber.toString()
                ),
                isQuestionLikedByStudent = isQuestionLikedByStudent
            )
        )
        solutionUiItems += createQuestionWithHtml(
            htmlString = questionModel.titleBodyMarkUp,
            staticUrl = staticUrl
        )

        if (answerModel.isHaveAnswer()) {
            createResultAnswerWithType(
                questionModel = questionModel,
                answerModel = answerModel,
                staticUrl = staticUrl
            )
        } else {
            createSolutionForAnswerWithType(questionModel = questionModel, staticUrl = staticUrl)
        }
        createBottomItems(countDividerBottomQuestion)
        return solutionUiItems
    }

    private fun createQuestionWithHtml(
        htmlString: String,
        staticUrl: String
    ): List<SolutionUiItem> {
        val solutionUiItems: MutableList<SolutionUiItem> = mutableListOf()
        val elements = Jsoup.parse(htmlString).body().children()
        elements.forEach { element ->
            val gravityAlign: GravityAlign = GravityAlign.getGravity(element.attr(H_ALIGN_ATTR))

            val itemImage: Elements = element.getElementsByTag(FILE_IMAGE_TAG)
            val isImageItem = itemImage.size != 0

            val itemVideo: Elements = element.getElementsByTag(EXTERNAL_VIDEO)
            val isYoutubeItem = itemVideo.attr(LINK).isNotEmpty()

            val itemSub: Elements = element.getElementsByTag(SUP_TEXT_TAG)
            val itemSup: Elements = element.getElementsByTag(SUB_TEXT_TAG)
            val isSupSubItem = itemSub.text().isNotEmpty() || itemSup.text().isNotEmpty()

            val text: String = element.text()
            val isTextItem = text.isNotEmpty() && !isSupSubItem
            val isDividerItem = text.isEmpty()

            when {
                isImageItem -> {
                    solutionUiItems.add(
                        ImageUiItem(
                            idImage = element.attr(FILE_ID_ATTR),
                            staticUrl = staticUrl
                        )
                    )
                }

                isYoutubeItem -> {
                    solutionUiItems.add(YoutubeUiItem(element.attr(LINK)))
                }

                isTextItem -> {
                    solutionUiItems.add(TextUiItem(element.toString(), gravityAlign))
                }

                isSupSubItem -> {
                    solutionUiItems.add(SupSubUiItem(element.toString(), gravityAlign))
                }

                isDividerItem -> {
                    solutionUiItems.add(DividerUiItem(DividerType.CONTENT_BACKGROUND))
                }
            }
        }
        return solutionUiItems
    }

    private fun createResultAnswerWithType(
        questionModel: QuestionModel,
        answerModel: AnswerModel,
        staticUrl: String
    ) {
        when (questionModel.testQuestionType) {
            TestQuestionType.SELECT_RIGHT_ANSWER_OR_ANSWERS -> {
                createSelectAnswersResult(
                    questionModel = questionModel,
                    answerModel = answerModel,
                )
            }

            TestQuestionType.TYPE_ANSWER_WITH_ERRORS -> {
                createResultAnswerWithErrors(
                    rightAnswer = questionModel.typeAnswerWithErrorsModel.rightAnswer,
                    answerModel = answerModel,
                )
            }

            TestQuestionType.TYPE_RIGHT_ANSWER -> {
                createResultRightAnswer(
                    rightAnswers = questionModel.typeRightAnswerQuestionModel.rightAnswers,
                    answerModel = answerModel,
                )
            }

            TestQuestionType.DETAILED_ANSWER -> {
                createDetailedResultAnswer(
                    questionModel = questionModel,
                    answerModel = answerModel,
                    staticUrl = staticUrl
                )
            }

            TestQuestionType.UNKNOWN -> {}
        }
    }

    private fun createSelectAnswersResult(
        questionModel: QuestionModel,
        answerModel: AnswerModel,
    ) {
        solutionUiItems.add(SelectRightAnswerTitleUiItem(questionModel.selectRightAnswerOrAnswersModel.selectRightAnswerTitle))

        val answerList =
            answerModel.answerOrAnswers.split(ANSWERS_DELIMITERS).filter { answerItem ->
                answerItem.isNotEmpty()
            }

        val rightAnswersList = questionModel.selectRightAnswerOrAnswersModel.answersList


        rightAnswersList.forEach { answerSelectRightModel ->
            solutionUiItems.add(
                answerSelectRightModel.toAnswerSelectRightUiModel(
                    questionId = questionModel.questionId,
                    enabled = false,
                    checked = answerList.contains(answerSelectRightModel.text)
                )
            )
        }

        solutionUiItems.add(
            ResultButtonUiItem(
                questionId = questionModel.questionId,
                answerValidationResultType = answerModel.answerValidationResultType,
                pointString = getPointString(answerModel)
            )
        )
    }

    private fun createResultAnswerWithErrors(
        rightAnswer: String,
        answerModel: AnswerModel,
    ) {
        val answer =
            answerModel.answerOrAnswers.ifEmpty { resourceProvider.string(UiCoreStrings.your_not_answer) }

        solutionUiItems.add(
            AnswerWithErrorsResultUiItem(
                questionId = answerModel.questionId,
                answer = answer,
                rightAnswer = rightAnswer,
                answerValidationResultType = answerModel.answerValidationResultType,
                pointString = getPointString(answerModel)
            )
        )
    }

    private fun createResultRightAnswer(
        rightAnswers: List<String>,
        answerModel: AnswerModel,
    ) {
        val answer =
            answerModel.answerOrAnswers.ifEmpty { resourceProvider.string(UiCoreStrings.your_not_answer) }

        val pointsString = with(answerModel) {
            if (pointsValidationModel.isValidated) {
                resourceProvider.string(
                    UiCoreStrings.points_question,
                    answerModel.pointsValidationModel.answerPoints,
                    answerModel.pointsValidationModel.questionTotalPoints
                )
            } else EMPTY_STRING
        }

        solutionUiItems.add(
            RightAnswerResultUiItem(
                questionId = answerModel.questionId,
                answer = answer,
                rightAnswer = rightAnswers.joinToString(separator = "\n"),
                answerValidationResultType = answerModel.answerValidationResultType,
                pointsString = pointsString
            )
        )
    }

    private fun createDetailedResultAnswer(
        questionModel: QuestionModel,
        answerModel: AnswerModel,
        staticUrl: String,
    ) {
        val answer =
            answerModel.answerOrAnswers.ifEmpty { resourceProvider.string(UiCoreStrings.your_not_answer) }
        val explanationList =
            createQuestionWithHtml(questionModel.answerExplanationMarkUp, staticUrl)
        val pointsValidationModel = answerModel.pointsValidationModel
        val isValidated = pointsValidationModel.isValidated

        solutionUiItems.add(DetailedAnswerYourAnswerUiItem(answer))
        solutionUiItems.addAll(explanationList)

        solutionUiItems.add(
            DetailedAnswerValidationUiItem(
                inputPoint = if (isValidated) pointsValidationModel.answerPoints.toString() else EMPTY_STRING,
                pointTotal = pointsValidationModel.questionTotalPoints.toString(),
                questionId = questionModel.questionId,
                type = if (isValidated) answerModel.answerValidationResultType else UNKNOWN,
                isValidated = isValidated,
                pointsString = getPointString(answerModel),
            )
        )
    }

    private fun getPointString(answerModel: AnswerModel): String {
        val pointsString =
            if (answerModel.pointsValidationModel.isValidated) {
                resourceProvider.string(
                    UiCoreStrings.points_question,
                    answerModel.pointsValidationModel.answerPoints,
                    answerModel.pointsValidationModel.questionTotalPoints
                )
            } else EMPTY_STRING
        return pointsString
    }

    private fun createSolutionForAnswerWithType(
        questionModel: QuestionModel,
        staticUrl: String
    ) {
        when (questionModel.testQuestionType) {
            TestQuestionType.SELECT_RIGHT_ANSWER_OR_ANSWERS -> createSelectAnswers(questionModel)
            TestQuestionType.TYPE_ANSWER_WITH_ERRORS -> createTypeAnswerWithErrors(questionModel)
            TestQuestionType.TYPE_RIGHT_ANSWER -> createTypeRightAnswer(questionModel)
            TestQuestionType.DETAILED_ANSWER -> createDetailedAnswer(questionModel, staticUrl)
            TestQuestionType.UNKNOWN -> {}
        }
    }

    private fun createSelectAnswers(questionModel: QuestionModel) {
        solutionUiItems.add(SelectRightAnswerTitleUiItem(questionModel.selectRightAnswerOrAnswersModel.selectRightAnswerTitle))
        val listAnswer = questionModel.selectRightAnswerOrAnswersModel.answersList
        listAnswer.forEach { answerSelectRightModel ->
            solutionUiItems.add(
                answerSelectRightModel.toAnswerSelectRightUiModel(
                    checked = false,
                    enabled = true,
                    questionId = questionModel.questionId
                )
            )
        }
        solutionUiItems.add(SelectRightAnswerCheckButtonUiItem(questionModel.questionId))
    }

    private fun createTypeAnswerWithErrors(questionModel: QuestionModel) {
        solutionUiItems.add(
            questionModel.toAnswerWithErrorsUiModel(
                rightAnswer = questionModel.typeAnswerWithErrorsModel.rightAnswer
            )
        )
    }

    private fun createTypeRightAnswer(questionModel: QuestionModel) {
        solutionUiItems.add(
            questionModel.toRightAnswerUiModel(
                rightAnswersList = questionModel.typeRightAnswerQuestionModel.rightAnswers,
                caseInSensitive = questionModel.typeRightAnswerQuestionModel.caseInSensitive,
            )
        )
    }

    private fun createDetailedAnswer(questionModel: QuestionModel, staticUrl: String) {
        val explanationList =
            createQuestionWithHtml(questionModel.answerExplanationMarkUp, staticUrl)
        solutionUiItems.add(
            DetailedAnswerInputUiItem(
                questionId = questionModel.questionId,
                inputAnswer = EMPTY_STRING,
                explanationList = explanationList,
            )
        )
    }

    private fun createBottomItems(countDividerBottomQuestion: Int) {
        solutionUiItems.add(FooterUiItem)
        repeat(countDividerBottomQuestion) {
            solutionUiItems.add(DividerUiItem(DividerType.BACKGROUND))
        }
    }

    companion object {
        private const val COUNT_DIVIDER_BOTTOM_QUESTION = 6
        private const val FILE_IMAGE_TAG = "file-image"
        private const val SUP_TEXT_TAG = "sup"
        private const val SUB_TEXT_TAG = "sub"
        private const val H_ALIGN_ATTR = "h-align"
        private const val FILE_ID_ATTR = "file-id"
        private const val EXTERNAL_VIDEO = "external-video"
        private const val LINK = "link"

        private const val ANSWERS_DELIMITERS = ";"
    }
}