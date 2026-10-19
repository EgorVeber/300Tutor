package org.v_points.data.solution.models

import com.google.gson.annotations.SerializedName
import org.v_points.data.solution.models.solution_response.AnswerResponse

class QuestionAnswerWithResultBaseApiResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val answerResponse: AnswerResponse?
)