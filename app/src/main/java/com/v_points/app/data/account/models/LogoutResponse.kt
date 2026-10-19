package com.v_points.app.data.account.models

import com.google.gson.annotations.SerializedName
import com.v_points.app.data.common.network.LogoutErrorType

class LogoutResponse(
    @SerializedName(value = "succeeded", alternate = ["isSucceeded"])
    val isSucceeded: Boolean?,
    @SerializedName("errorMessage") val message: String?,
    @SerializedName("errorType") val logoutErrorType: LogoutErrorType?,
)
