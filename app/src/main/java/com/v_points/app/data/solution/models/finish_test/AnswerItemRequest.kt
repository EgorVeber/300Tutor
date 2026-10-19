package com.v_points.app.data.solution.models.finish_test

import com.google.gson.annotations.SerializedName

class AnswerItemRequest(
    @SerializedName("answerOrAnswers")
    val answerOrAnswers: String,
    @SerializedName("questionId")
    val questionId: String
)