package org.v_points.data.solution.models.solution_response

import com.google.gson.annotations.SerializedName

class SolutionResponse(
    @SerializedName("hasAnswersInProccess")
    val hasAnswersInProccess: Boolean?,
    @SerializedName("answers")
    val answerResponse: List<AnswerResponse>?
)