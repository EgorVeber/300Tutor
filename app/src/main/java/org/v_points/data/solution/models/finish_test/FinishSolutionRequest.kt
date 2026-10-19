package org.v_points.data.solution.models.finish_test

import com.google.gson.annotations.SerializedName

class FinishSolutionRequest(
    @SerializedName("answers")
    val answerList: List<AnswerItemRequest>,
    @SerializedName("solutionId")
    val solutionId: String
)