package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import org.v_points.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.v_points.domain.solution.models.solution_models.AnswerModel
import org.v_points.ui_common.util.orFalse

fun QuestionAnswerWithResultBaseApiResponse.toQuestionAnswerWithResultBaseApiModel(): QuestionAnswerWithResultBaseApiModel =
    QuestionAnswerWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answerModel = answerResponse?.toAnswerModel() ?: AnswerModel.EMPTY
    )
