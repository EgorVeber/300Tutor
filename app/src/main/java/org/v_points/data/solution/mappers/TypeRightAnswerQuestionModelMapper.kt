package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.test_response.TypeRightAnswerQuestionDataResponse
import org.v_points.domain.solution.models.test_model.TypeRightAnswerQuestionModel
import org.v_points.ui_common.util.orFalse

fun TypeRightAnswerQuestionDataResponse.toTypeRightAnswerQuestionModel(): TypeRightAnswerQuestionModel =
    TypeRightAnswerQuestionModel(
        caseInSensitive = caseInSensitive.orFalse(),
        rightAnswers = rightAnswers ?: emptyList()
    )