package com.v_points.app.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class AnswerXResponse(
    @SerializedName("isRightAnswer")
    val isRightAnswer: Boolean?,
    @SerializedName("text")
    val text: String?
)