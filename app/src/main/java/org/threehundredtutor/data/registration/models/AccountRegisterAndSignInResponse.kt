package org.threehundredtutor.data.registration.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.authorization.models.LoginResponse

class AccountRegisterAndSignInResponse(
    @SerializedName("registrationResult")
    val registerResponse: RegisterResponse?,
    @SerializedName("loginResult")
    val loginResponse: LoginResponse?,
)
