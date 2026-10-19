package com.v_points.app.data.main.response

import com.google.gson.annotations.SerializedName

class CreateLinkTelegramResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val responseObject: ResponseObject?
)