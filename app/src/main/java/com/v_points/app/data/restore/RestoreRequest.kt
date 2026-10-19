package com.v_points.app.data.restore

import com.google.gson.annotations.SerializedName

class RestoreRequest(
    @SerializedName("email")
    val email: String,
)
