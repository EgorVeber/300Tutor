package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.test_response.TypeRightAnswerQuestionDataResponse
import org.threehundredtutor.domain.solution.models.test_model.TypeRightAnswerQuestionModel

fun TypeRightAnswerQuestionDataResponse.toTypeRightAnswerQuestionModel(): TypeRightAnswerQuestionModel =
    TypeRightAnswerQuestionModel(
        caseInSensitive = caseInSensitive.orFalse(),
        rightAnswers = rightAnswers ?: emptyList()
    )