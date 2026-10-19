package org.v_points.domain.registration.models

import org.v_points.domain.authorization.LoginModel

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
