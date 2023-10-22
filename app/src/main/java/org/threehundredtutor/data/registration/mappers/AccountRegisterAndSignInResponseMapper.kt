package org.threehundredtutor.data.registration.mappers

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.data.authorization.mapper.toLoginModel
import org.threehundredtutor.data.registration.models.AccountRegisterAndSignInResponse
import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel

fun AccountRegisterAndSignInResponse.toAccountRegisterAndSignInResponseMapper(): RegistrationAccountAndSignInModel =
    RegistrationAccountAndSignInModel(
        registrationModel = registerResponse?.toRegistrationModelMapper()
            ?: throw BadRequestException(),
        loginModel = loginResponse?.toLoginModel() ?: throw BadRequestException()
    )
