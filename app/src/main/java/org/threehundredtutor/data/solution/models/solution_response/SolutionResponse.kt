package org.threehundredtutor.data.solution.models.solution_response

import com.google.gson.annotations.SerializedName

class SolutionResponse(
    @SerializedName("answers")
    val answerResponse: List<AnswerResponse>?
)