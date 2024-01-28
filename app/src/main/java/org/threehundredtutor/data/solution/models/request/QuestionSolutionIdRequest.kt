package org.threehundredtutor.data.solution.models.request

import com.google.gson.annotations.SerializedName

class QuestionSolutionIdRequest(
    @SerializedName("questionId")
    val questionId: String,
    @SerializedName("solutionId")
    val solutionId: String
)