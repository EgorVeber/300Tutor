package com.v_points.app.data.main.response

import com.google.gson.annotations.SerializedName

class CourseProgressResponse(
    @SerializedName("currentProgress")
    val currentProgress: Int?,
    @SerializedName("progressPercents")
    val progressPercents: Int?,
    @SerializedName("totalWeight")
    val totalWeight: Int?
)

