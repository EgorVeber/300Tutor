package com.v_points.app.data.solution.models.request

import com.google.gson.annotations.SerializedName

class CheckAnswerRequest(
    @SerializedName("solutionId") val solutionId: String,
    @SerializedName("answer") val answer: AnswerRequest,
)


