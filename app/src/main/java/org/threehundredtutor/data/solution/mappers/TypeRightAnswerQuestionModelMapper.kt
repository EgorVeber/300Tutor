package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.test_response.TypeRightAnswerQuestionDataResponse
import org.threehundredtutor.domain.solution.models.test_model.TypeRightAnswerQuestionModel
import org.threehundredtutor.ui_common.util.orFalse

fun TypeRightAnswerQuestionDataResponse.toTypeRightAnswerQuestionModel(): TypeRightAnswerQuestionModel =
    TypeRightAnswerQuestionModel(
        caseInSensitive = caseInSensitive.orFalse(),
        rightAnswers = rightAnswers ?: emptyList()
    )