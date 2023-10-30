package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.solution_response.PointsValidationResponse
import org.threehundredtutor.domain.solution.models.solution_models.PointsValidationModel

fun PointsValidationResponse.toPointsValidationModel(): PointsValidationModel =
    PointsValidationModel(
        answerPoints = answerPoints.orDefaultNotValidValue(),
        description = description.orEmpty(),
        isValidated = isValidated.orFalse(),
        // TODO возвращает 0, как заработает поменять на questionTotalPoints.orDefaultNotValidValue().
        questionTotalPoints = if (questionTotalPoints == 0 || questionTotalPoints == null) 3 else questionTotalPoints,
    )
