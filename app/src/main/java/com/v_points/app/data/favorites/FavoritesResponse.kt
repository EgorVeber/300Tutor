package com.v_points.app.data.favorites


import com.google.gson.annotations.SerializedName
import com.v_points.app.data.solution.models.test_response.QuestionResponse

class FavoritesResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("list") val list: List<QuestionResponse>?,
    @SerializedName("offSet") val offSet: Int?,
    @SerializedName("totalCount") val totalCount: Int?
)