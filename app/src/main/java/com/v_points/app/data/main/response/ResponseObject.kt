package com.v_points.app.data.main.response

import com.google.gson.annotations.SerializedName

class ResponseObject(
    @SerializedName("command")
    val command: String?
)