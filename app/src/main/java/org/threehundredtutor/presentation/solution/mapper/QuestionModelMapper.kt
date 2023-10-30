package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.domain.solution.models.test_model.AnswerXModel
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.presentation.solution.models.answer.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.models.answer.AnswerXUiModel
import org.threehundredtutor.presentation.solution.models.answer.RightAnswerUiModel

fun QuestionModel.toRightAnswerUiModel(
    rightAnswersList: List<String>,
    caseInSensitive: Boolean
): RightAnswerUiModel =
    RightAnswerUiModel(
        questionId = id,
        title = title,
        testQuestionType = testQuestionType,
        caseInSensitive = caseInSensitive,
        rightAnswers = rightAnswersList
    )

fun QuestionModel.toAnswerWithErrorsUiModel(
    rightAnswer: String
): AnswerWithErrorsUiModel =
    AnswerWithErrorsUiModel(
        questionId = id,
        title = title,
        testQuestionType = testQuestionType,
        rightAnswer = rightAnswer,
    )


fun AnswerXModel.toAnswerXUiModel(questionId: String): AnswerXUiModel =
    AnswerXUiModel(
        rightAnswer = isRightAnswer,
        answer = text,
        checked = false,
        enabled = true,
        questionId = questionId,
    )
