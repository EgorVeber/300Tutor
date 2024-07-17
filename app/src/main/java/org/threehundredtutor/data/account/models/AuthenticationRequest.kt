package org.threehundredtutor.data.account.models

import com.google.gson.annotations.SerializedName


class AuthenticationRequest(
    @SerializedName("useRedirectLink")
    val useRedirectLink: Boolean,
    @SerializedName("redirectLink")
    val redirectLink: String ,
)