package org.threehundredtutor.data.registration.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.common.network.ErrorType

class RegisterResponse(
    @SerializedName("succeeded")
    val succeeded: Boolean?,
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("errorType")
    val errorType: ErrorType?,
    @SerializedName("registeredUser")
    val registeredUser: RegisteredUserResponse?
)
