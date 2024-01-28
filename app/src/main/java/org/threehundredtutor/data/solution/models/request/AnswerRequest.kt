package org.threehundredtutor.data.solution.models.request

import com.google.gson.annotations.SerializedName

class AnswerRequest(
    @SerializedName("questionId") val questionId: String,
    @SerializedName("answerOrAnswers") val answerOrAnswers: String,
)