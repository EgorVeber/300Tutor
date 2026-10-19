package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.test_response.TypeAnswerWithErrorsDataResponse
import org.v_points.domain.solution.models.test_model.TypeAnswerWithErrorsModel

fun TypeAnswerWithErrorsDataResponse.toTypeAnswerWithErrorsModel(): TypeAnswerWithErrorsModel =
    TypeAnswerWithErrorsModel(
        rightAnswer = rightAnswer.orEmpty()
    )