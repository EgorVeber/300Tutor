package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.test_response.TypeRightAnswerQuestionDataResponse
import com.v_points.app.domain.solution.models.test_model.TypeRightAnswerQuestionModel
import com.v_points.app.ui_common.util.orFalse

fun TypeRightAnswerQuestionDataResponse.toTypeRightAnswerQuestionModel(): TypeRightAnswerQuestionModel =
    TypeRightAnswerQuestionModel(
        caseInSensitive = caseInSensitive.orFalse(),
        rightAnswers = rightAnswers ?: emptyList()
    )