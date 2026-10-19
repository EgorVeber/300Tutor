package com.v_points.app.data.main.request

import com.google.gson.annotations.SerializedName

class EnterGroupRequest(
    @SerializedName("enterCode")
    val enterCode: String
)