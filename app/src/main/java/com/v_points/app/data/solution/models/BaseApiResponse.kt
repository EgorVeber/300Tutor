package com.v_points.app.data.solution.models

import com.google.gson.annotations.SerializedName

class BaseApiResponse(
    @SerializedName("isSucceeded") val isSucceeded: Boolean?,
    @SerializedName("message") val message: String?
)