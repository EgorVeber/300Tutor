package org.threehundredtutor.data.solution.models

import com.google.gson.annotations.SerializedName

class QuestionAnswersWithResultBaseApiResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val answerResponse: AnswersResponse?
)