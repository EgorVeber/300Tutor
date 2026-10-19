package com.v_points.app.domain.registration.models

import com.v_points.app.data.common.network.ErrorType

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
