package com.v_points.app.domain.registration.models

import com.v_points.app.domain.authorization.LoginModel

data class RegistrationAccountAndSignInModel(
    val registrationModel: RegistrationModel,
    val loginModel: LoginModel
) {
    companion object {
        fun empty(): RegistrationAccountAndSignInModel =
            RegistrationAccountAndSignInModel(
                registrationModel = RegistrationModel.empty(),
                loginModel = LoginModel.empty()
            )
    }
}
