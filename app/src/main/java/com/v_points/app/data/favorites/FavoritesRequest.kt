package com.v_points.app.data.favorites

import com.google.gson.annotations.SerializedName

class FavoritesRequest(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offSet")
    val offSet: Int,
    @SerializedName("studentId")
    val studentId: String,
)