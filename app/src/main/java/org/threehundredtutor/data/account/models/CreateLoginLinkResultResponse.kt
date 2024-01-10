package org.threehundredtutor.data.account.models

import com.google.gson.annotations.SerializedName

class CreateLoginLinkResultResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("linkId")
    val linkId: String?,
    @SerializedName("password")
    val password: String?,
)
