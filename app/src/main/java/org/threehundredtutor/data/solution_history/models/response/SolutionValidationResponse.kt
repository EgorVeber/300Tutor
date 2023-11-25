package org.threehundredtutor.data.solution_history.models.response

import com.google.gson.annotations.SerializedName

class SolutionValidationResponse(
    @SerializedName("hasRightAnswerQuestionsCount")
    val hasRightAnswerQuestionsCount: Int?,
    @SerializedName("maxTotalPoints")
    val maxTotalPoints: Int?,
    @SerializedName("questionsCount")
    val questionsCount: Int?,
    @SerializedName("studentTotalPoints")
    val studentTotalPoints: Int?,
    @SerializedName("validatedQuestionsCount")
    val validatedQuestionsCount: Int?
)