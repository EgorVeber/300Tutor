package org.threehundredtutor.data.solution.models.points

import com.google.gson.annotations.SerializedName

class SolutionPointsResponse(
    @SerializedName("hasPointsResult")
    val hasPointsResult: Boolean?,
    @SerializedName("hasRightAnswerQuestionsCount")
    val hasRightAnswerQuestionsCount: Int?,
    @SerializedName("maxTotalPoints")
    val maxTotalPoints: Int?,
    @SerializedName("noPointsValidation")
    val noPointsValidation: Boolean?,
    @SerializedName("questions")
    val solutionPointsQuestionResponses: List<SolutionPointsQuestionResponse>?,
    @SerializedName("questionsCount")
    val questionsCount: Int?,
    @SerializedName("solutionId")
    val solutionId: String?,
    @SerializedName("studentTotalPoints")
    val studentTotalPoints: Int?,
    @SerializedName("validatedQuestionsCount")
    val validatedQuestionsCount: Int?,
    @SerializedName("inProccessQuestionsCount")
    val inProccessQuestionsCount: Int?
)