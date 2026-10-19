package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import org.v_points.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.v_points.ui_common.util.orFalse

fun QuestionAnswersWithResultBaseApiResponse.toQuestionAnswersWithResultBaseApiModel(): QuestionAnswersWithResultBaseApiModel =
    QuestionAnswersWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answersModel = answerResponse?.answersResponse?.map { answerResponse ->
            answerResponse.toAnswerModel()
        }.orEmpty()
    )
