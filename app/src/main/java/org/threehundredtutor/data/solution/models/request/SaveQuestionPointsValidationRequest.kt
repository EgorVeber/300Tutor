package org.threehundredtutor.data.solution.models.request

import com.google.gson.annotations.SerializedName

class SaveQuestionPointsValidationRequest(
    @SerializedName("idModel")
    val questionSolutionIdRequest: QuestionSolutionIdRequest,
    @SerializedName("answerPoints")
    val answerPoints: Int,
    @SerializedName("questionTotalPoints")
    val questionTotalPoints: Int,
    @SerializedName("description")
    val description: String
)