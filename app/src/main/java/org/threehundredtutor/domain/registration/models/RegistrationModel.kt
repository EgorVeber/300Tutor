package org.threehundredtutor.domain.registration.models

import org.threehundredtutor.data.core.models.ErrorType

data class RegistrationModel(
    val succeded: Boolean,
    val errorMessage: String,
    val errorType: ErrorType,
    val registeredUser: RegisteredUserModel
) {
    companion object {
        fun empty(): RegistrationModel = RegistrationModel(
            succeded = false,
            errorMessage = "",
            errorType = ErrorType.NONE,
            registeredUser = RegisteredUserModel.empty()
        )
    }
}