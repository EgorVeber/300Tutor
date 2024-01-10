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
    val questionTotalPoints: Int?,
    @SerializedName("errorOccured")
    val errorOccured: Boolean?,
    @SerializedName("inProccess")
    val inProccess: Boolean?,
    @SerializedName("sourceType")
    val sourceType: String?, //System, Student, Curator ENUM
    @SerializedName("gptSystemType")
    val gptSystemType: String?, // Default, Gpt, GigaChat ENUM
    @SerializedName("validatorId")
    val validatorId: String?,
)