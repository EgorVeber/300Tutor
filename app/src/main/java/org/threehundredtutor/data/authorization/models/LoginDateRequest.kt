package org.threehundredtutor.data.authorization.models

import com.google.gson.annotations.SerializedName

class LoginDateRequest(
    @SerializedName("password") val password: String,
    @SerializedName("rememberMe") val rememberMe: Boolean,
    @SerializedName("emailOrPhoneNumber") val emailOrPhoneNumber: String,
)