package com.v_points.app.data.solution.models

import com.google.gson.annotations.SerializedName
import com.v_points.app.data.solution.models.solution_response.AnswerResponse

class AnswersResponse(
    @SerializedName("answers")
    val answersResponse: List<AnswerResponse>?
)