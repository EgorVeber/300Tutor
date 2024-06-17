package org.threehundredtutor.data.registration.models

import com.google.gson.annotations.SerializedName

class RegisteRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("noEmail")
    val noEmail: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("noPhoneNumber")
    val noPhoneNumber: Boolean,
    @SerializedName("password")
    val password: String,
)
