package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import com.v_points.app.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import com.v_points.app.ui_common.util.orFalse

fun QuestionAnswersWithResultBaseApiResponse.toQuestionAnswersWithResultBaseApiModel(): QuestionAnswersWithResultBaseApiModel =
    QuestionAnswersWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answersModel = answerResponse?.answersResponse?.map { answerResponse ->
            answerResponse.toAnswerModel()
        }.orEmpty()
    )
