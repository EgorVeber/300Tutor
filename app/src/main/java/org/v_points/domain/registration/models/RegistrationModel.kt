package org.v_points.domain.registration.models

import org.v_points.data.common.network.ErrorType

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
