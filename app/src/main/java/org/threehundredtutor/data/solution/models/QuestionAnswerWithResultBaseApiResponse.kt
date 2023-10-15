package org.threehundredtutor.data.solution.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.solution_response.AnswerResponse

class QuestionAnswerWithResultBaseApiResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")// TODO обсудить с менеджером
    val answerResponse: AnswerResponse?
)