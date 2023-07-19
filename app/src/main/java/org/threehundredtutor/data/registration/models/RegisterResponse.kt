package org.threehundredtutor.data.registration.models

import org.threehundredtutor.data.core.models.ErrorType

class RegisterResponse(
    val succeeded: Boolean?,
    val errorMessage: String?,
    val errorType: ErrorType?,
    val registeredUser: RegisteredUserResponse?
)
