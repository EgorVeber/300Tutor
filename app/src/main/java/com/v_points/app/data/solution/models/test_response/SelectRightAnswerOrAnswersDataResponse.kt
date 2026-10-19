package com.v_points.app.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class SelectRightAnswerOrAnswersDataResponse(
    @SerializedName("answers")
    val answers: List<AnswerXResponse>?,
    @SerializedName("rightAnswersCount")
    val rightAnswersCount: Int?,
    @SerializedName("selectRightAnswerTitle")
    val selectRightAnswerTitle: String?
)