package org.threehundredtutor.data.solution.models.solution_response

import com.google.gson.annotations.SerializedName

class PointsValidationResponse(
    @SerializedName("answerPoints")
    val answerPoints: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("isValidated")
    val isValidated: Boolean?,
    @SerializedName("questionTotalPoints")
    val questionTotalPoints: Int?
)