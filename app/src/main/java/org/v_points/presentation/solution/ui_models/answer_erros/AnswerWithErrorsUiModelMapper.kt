package org.v_points.presentation.solution.ui_models.answer_erros

import org.v_points.domain.solution.models.test_model.QuestionModel
import org.v_points.ui_common.EMPTY_STRING

fun QuestionModel.toAnswerWithErrorsUiModel(
    rightAnswer: String
): AnswerWithErrorsUiModel =
    AnswerWithErrorsUiModel(
        questionId = questionId,
        title = title,
        rightAnswer = rightAnswer,
        inputAnswer = EMPTY_STRING
    )

fun QuestionModel.toAnswerWithErrorsUiModel(
    rightAnswer: String,
    inputAnswer:String
): AnswerWithErrorsUiModel =
    AnswerWithErrorsUiModel(
        questionId = questionId,
        title = title,
        rightAnswer = rightAnswer,
        inputAnswer = inputAnswer
    )