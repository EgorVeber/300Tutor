package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.test_response.TypeAnswerWithErrorsDataResponse
import org.threehundredtutor.domain.solution.models.test_model.TypeAnswerWithErrorsModel

fun TypeAnswerWithErrorsDataResponse.toTypeAnswerWithErrorsModel(): TypeAnswerWithErrorsModel =
    TypeAnswerWithErrorsModel(
        rightAnswer = rightAnswer.orEmpty()
    )