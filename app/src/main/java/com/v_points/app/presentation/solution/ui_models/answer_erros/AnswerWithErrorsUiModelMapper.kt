package com.v_points.app.presentation.solution.ui_models.answer_erros

import com.v_points.app.domain.solution.models.test_model.QuestionModel
import com.v_points.app.ui_common.EMPTY_STRING

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