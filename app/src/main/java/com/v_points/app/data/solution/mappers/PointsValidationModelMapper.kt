package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.solution_response.PointsValidationResponse
import com.v_points.app.domain.solution.models.solution_models.PointsValidationModel
import com.v_points.app.ui_common.util.orDefaultNotValidValue
import com.v_points.app.ui_common.util.orFalse

fun PointsValidationResponse.toPointsValidationModel(): PointsValidationModel =
    PointsValidationModel(
        answerPoints = answerPoints.orDefaultNotValidValue(),
        description = description.orEmpty(),
        isValidated = isValidated.orFalse(),
        // TODO возвращает 0, как заработает поменять на questionTotalPoints.orDefaultNotValidValue().
        questionTotalPoints = if (questionTotalPoints == 0 || questionTotalPoints == null) 3 else questionTotalPoints,
    )
