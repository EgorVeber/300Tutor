package org.threehundredtutor.presentation.solution.html_helper

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.presentation.solution.mapper.toAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.mapper.toAnswerXUiModel
import org.threehundredtutor.presentation.solution.mapper.toRightAnswerUiModel
import org.threehundredtutor.presentation.solution.models.item.DividerSolutionItem
import org.threehundredtutor.presentation.solution.models.item.FooterSolutionItem
import org.threehundredtutor.presentation.solution.models.item.ImageSolutionItem
import org.threehundredtutor.presentation.solution.models.SolutionItem
import org.threehundredtutor.presentation.solution.models.item.SupSubSolutionItem
import org.threehundredtutor.presentation.solution.models.item.TextSolutionItem
import org.threehundredtutor.presentation.solution.models.answer.TitleAnswerUiItem
import org.threehundredtutor.presentation.solution.models.item.TitleSolutionItem
import org.threehundredtutor.presentation.solution.models.item.YoutubeUiItem
import org.threehundredtutor.presentation.solution.models.answer.DetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.models.check.CheckButtonUiItem
import javax.inject.Inject

class SolutionFactory @Inject constructor() {
    private var solutionItems: MutableList<SolutionItem> = mutableListOf()
    fun createSolution(questionList: List<QuestionModel>): List<SolutionItem> =
        questionList.flatMap { question -> createSolutionItems(question) }

    private fun createSolutionItems(
        questionModel: QuestionModel,
        countDividerBottomQuestion: Int = COUNT_DIVIDER_BOTTOM_QUESTION,
    ): List<SolutionItem> {
        solutionItems.clear()
        // TODO придумать как строить разметку из answerExplanationMarkUp TutorAndroid-25
        val elements = Jsoup.parse(questionModel.titleBodyMarkUp).body().children()

        createQuestionWithElement(elements, questionModel.title)
        createAnswerWithType(questionModel.testQuestionType, questionModel)
        createBottomItems(countDividerBottomQuestion)

        return solutionItems
    }

    private fun createQuestionWithElement(elements: Elements, title: String) {
        solutionItems.add(TitleSolutionItem(title))
        elements.forEach { element ->
            val itemImage: Elements = element.getElementsByTag(FILE_IMAGE_TAG)
            val itemSub: Elements = element.getElementsByTag(SUP_TEXT_TAG)
            val itemSup: Elements = element.getElementsByTag(SUB_TEXT_TAG)
            val itemVideo: Elements = element.getElementsByTag(EXTERNAL_VIDEO)
            val text: String = element.text()
            val isTextItem = itemSub.size == 0 && itemSup.size == 0
            val attrAlign = element.attr(H_ALIGN_ATTR)
            val gravityAlign: GravityAlign = GravityAlign.getGravity(attrAlign)

            when {
                itemImage.size != 0 -> solutionItems.add(ImageSolutionItem(element.attr(FILE_ID_ATTR)))

                itemVideo.attr(LINK).isNotEmpty() ->
                    solutionItems.add(YoutubeUiItem(element.attr(LINK)))

                text.isEmpty() -> solutionItems.add(DividerSolutionItem(DividerColor.CONTENT_BACKGROUND))

                isTextItem -> solutionItems.add(TextSolutionItem(element.toString(), gravityAlign))

                !isTextItem ->
                    solutionItems.add(SupSubSolutionItem(element.toString(), gravityAlign))
            }
        }
    }

    private fun createBottomItems(countDividerBottomQuestion: Int) {
        solutionItems.add(FooterSolutionItem())
        repeat(countDividerBottomQuestion) {
            solutionItems.add(DividerSolutionItem(DividerColor.BACKGROUND))
        }
    }

    private fun createAnswerWithType(
        testQuestionType: TestQuestionType,
        questionModel: QuestionModel,
    ) {
        when (testQuestionType) {
            TestQuestionType.SELECT_RIGHT_ANSWER_OR_ANSWERS -> createSelectAnswers(questionModel)
            TestQuestionType.TYPE_ANSWER_WITH_ERRORS -> createTypeAnswerWithErrors(questionModel)
            TestQuestionType.TYPE_RIGHT_ANSWER -> createTypeRightAnswer(questionModel)
            TestQuestionType.DETAILED_ANSWER -> createDetailedAnswer(questionModel)
            TestQuestionType.UNKNOWN -> {}
        }
    }

    private fun createSelectAnswers(questionModel: QuestionModel) {
        solutionItems.add(TitleAnswerUiItem(questionModel.selectRightAnswerOrAnswersDataModel.selectRightAnswerTitle))
        val listAnswer = questionModel.selectRightAnswerOrAnswersDataModel.answersList
        listAnswer.forEach {
            solutionItems.add(it.toAnswerXUiModel(questionModel.id))
        }
        solutionItems.add(CheckButtonUiItem(questionModel.id))
    }

    private fun createTypeAnswerWithErrors(questionModel: QuestionModel) {
        solutionItems.add(
            questionModel.toAnswerWithErrorsUiModel(
                rightAnswer = questionModel.typeAnswerWithErrorsDataModel.rightAnswer
            )
        )
    }

    private fun createTypeRightAnswer(questionModel: QuestionModel) {
        solutionItems.add(
            questionModel.toRightAnswerUiModel(
                rightAnswersList = questionModel.typeRightAnswerQuestionDataModel.rightAnswers,
                caseInSensitive = questionModel.typeRightAnswerQuestionDataModel.caseInSensitive,
            )
        )
    }

    private fun createDetailedAnswer(questionModel: QuestionModel) {
        solutionItems.add(DetailedAnswerUiModel(questionModel.id))
    }

    companion object {
        private const val COUNT_DIVIDER_BOTTOM_QUESTION = 4
        private const val FILE_IMAGE_TAG = "file-image"
        private const val SUP_TEXT_TAG = "sup"
        private const val SUB_TEXT_TAG = "sub"
        private const val H_ALIGN_ATTR = "h-align"
        private const val FILE_ID_ATTR = "file-id"
        private const val EXTERNAL_VIDEO = "external-video"
        private const val LINK = "link"
    }
}