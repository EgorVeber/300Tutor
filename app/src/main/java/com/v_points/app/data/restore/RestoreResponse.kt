package com.v_points.app.data.restore

import com.google.gson.annotations.SerializedName
class RestoreResponse(
    @SerializedName("isSucceeded") val isSucceeded: Boolean?,
    @SerializedName("message") val message: String?,
)
