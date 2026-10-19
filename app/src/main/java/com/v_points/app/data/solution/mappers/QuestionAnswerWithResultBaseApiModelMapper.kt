package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import com.v_points.app.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import com.v_points.app.domain.solution.models.solution_models.AnswerModel
import com.v_points.app.ui_common.util.orFalse

fun QuestionAnswerWithResultBaseApiResponse.toQuestionAnswerWithResultBaseApiModel(): QuestionAnswerWithResultBaseApiModel =
    QuestionAnswerWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answerModel = answerResponse?.toAnswerModel() ?: AnswerModel.EMPTY
    )
