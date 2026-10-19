package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.solution_response.PointsValidationResponse
import org.v_points.domain.solution.models.solution_models.PointsValidationModel
import org.v_points.ui_common.util.orDefaultNotValidValue
import org.v_points.ui_common.util.orFalse

fun PointsValidationResponse.toPointsValidationModel(): PointsValidationModel =
    PointsValidationModel(
        answerPoints = answerPoints.orDefaultNotValidValue(),
        description = description.orEmpty(),
        isValidated = isValidated.orFalse(),
        // TODO возвращает 0, как заработает поменять на questionTotalPoints.orDefaultNotValidValue().
        questionTotalPoints = if (questionTotalPoints == 0 || questionTotalPoints == null) 3 else questionTotalPoints,
    )
