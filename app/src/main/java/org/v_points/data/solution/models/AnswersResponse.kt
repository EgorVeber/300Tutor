package org.v_points.data.solution.models

import com.google.gson.annotations.SerializedName
import org.v_points.data.solution.models.solution_response.AnswerResponse

class AnswersResponse(
    @SerializedName("answers")
    val answersResponse: List<AnswerResponse>?
)