package org.threehundredtutor.data.solution.models.solution_response

import com.google.gson.annotations.SerializedName

class AnswerResponse(
    @SerializedName("answerOrAnswers")
    val answerOrAnswers: String?,
    @SerializedName("isChecked")
    val isChecked: Boolean?,
    @SerializedName("pointsValidationModel")
    val pointsValidationResponse: PointsValidationResponse?,
    @SerializedName("questionId")
    val questionId: String?,
    @SerializedName("questionVersionId")
    val questionVersionId: String?,
    @SerializedName("resultType")
    val resultType: String?
)