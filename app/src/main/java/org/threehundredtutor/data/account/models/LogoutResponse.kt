package org.threehundredtutor.data.account.models

import com.google.gson.annotations.SerializedName

class LogoutResponse(
    @SerializedName("isSucceeded") val isSucceeded: Boolean?,
    @SerializedName("message") val message: String?,
)
