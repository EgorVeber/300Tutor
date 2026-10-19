package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.test_response.TypeAnswerWithErrorsDataResponse
import com.v_points.app.domain.solution.models.test_model.TypeAnswerWithErrorsModel

fun TypeAnswerWithErrorsDataResponse.toTypeAnswerWithErrorsModel(): TypeAnswerWithErrorsModel =
    TypeAnswerWithErrorsModel(
        rightAnswer = rightAnswer.orEmpty()
    )