package org.threehundredtutor.data.account.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.common.network.LogoutErrorType

class LogoutResponse(
    @SerializedName(value = "succeeded", alternate = ["isSucceeded"])
    val isSucceeded: Boolean?,
    @SerializedName("errorMessage") val message: String?,
    @SerializedName("errorType") val logoutErrorType: LogoutErrorType?,
)
