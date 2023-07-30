package org.threehundredtutor.data.registration.models

import com.google.gson.annotations.SerializedName

class RegisteredUserResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("email")
    val email: String?,
)
