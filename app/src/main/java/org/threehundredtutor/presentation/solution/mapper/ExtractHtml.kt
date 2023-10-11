package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.test_model.AnswerXModel
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.presentation.solution.html_helper.HtmlHelper
import org.threehundredtutor.presentation.solution.model.AnswerCheckedUiModel
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.QuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.model.QuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.model.QuestionRightAnswerUiModel
import org.threehundredtutor.presentation.solution.model.SelectRightAnswerOrAnswersUiModel

fun QuestionModel.extractHtml(solutionId: String): List<HtmlItem> =
    HtmlHelper.getHtmlItemList(this, solutionId = solutionId)

// SelectRightAnswerOrAnswers, TypeRightAnswer, DetailedAnswer, TypeAnswerWithErrors ]
fun QuestionModel.toQuestionRightAnswerUiModel(
    rightAnswers: List<String>,
    caseInSensitive: Boolean
): QuestionRightAnswerUiModel =
    QuestionRightAnswerUiModel(
        questionId = id,
        title = title,
        testQuestionType = testQuestionType,
        caseInSensitive = caseInSensitive,
        rightAnswers = rightAnswers
    )

fun QuestionModel.toQuestionAnswerWithErrorsUiModel(
    solutionId: String,
    rightAnswer: String
): QuestionAnswerWithErrorsUiModel =
    QuestionAnswerWithErrorsUiModel(
        solutionId = solutionId,
        questionId = id,
        title = title,
        testQuestionType = testQuestionType,
        rightAnswer = rightAnswer,
    )


// TODO возмождно моджель не нудно вообще
fun QuestionModel.toQuestionDetailedAnswerUiModel(
    solutionId: String,
): QuestionDetailedAnswerUiModel =
    QuestionDetailedAnswerUiModel(
        solutionId = solutionId,
        questionId = id,
        testQuestionType = testQuestionType,
    )

fun QuestionModel.toSelectRightAnswerOrAnswersUiModel(
    solutionId: String,
    id: String,
): SelectRightAnswerOrAnswersUiModel =
    SelectRightAnswerOrAnswersUiModel(
        solutionId = solutionId,
        questionId = id,
        testQuestionType = testQuestionType,
        selectRightAnswerTitle = selectRightAnswerOrAnswersDataModel.selectRightAnswerTitle,
        rightAnswersCount = selectRightAnswerOrAnswersDataModel.rightAnswersCount,
        answers = selectRightAnswerOrAnswersDataModel.answers.map { it.toAnswerCheckedUiModel(id) },
        answerValidationResultType = AnswerValidationResultType.UNKNOWN
    )

fun AnswerXModel.toAnswerCheckedUiModel(questionId: String): AnswerCheckedUiModel =
    AnswerCheckedUiModel(
        isRightAnswer = isRightAnswer,
        text = text,
        checked = false,
        enabled = true,
        questionId = questionId
    )

