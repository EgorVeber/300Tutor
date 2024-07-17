package org.threehundredtutor.domain.solution.models.solution_models

import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_common.EMPTY_STRING

data class PointsValidationModel(
    val answerPoints: Int,
    val description: String,
    val isValidated: Boolean,
    val questionTotalPoints: Int
) {
    companion object {
        val EMPTY = PointsValidationModel(
            answerPoints = DEFAULT_NOT_VALID_VALUE_INT,
            description = EMPTY_STRING,
            isValidated = false,
            questionTotalPoints = DEFAULT_NOT_VALID_VALUE_INT,
        )
    }
}