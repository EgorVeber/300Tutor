package org.threehundredtutor.data.solution.models.points

import com.google.gson.annotations.SerializedName

class SolutionPointsQuestionResponse(
    @SerializedName("answerPoints")
    val answerPoints: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("isValidated")
    val isValidated: Boolean?,
    @SerializedName("questionId")
    val questionId: String?,
    @SerializedName("questionTotalPoints")
    val questionTotalPoints: Int?,
    @SerializedName("resultType")
    val resultType: String?,
    @SerializedName("sourceType")
    val sourceType: String?,
    @SerializedName("validatorId")
    val validatorId: String?
)