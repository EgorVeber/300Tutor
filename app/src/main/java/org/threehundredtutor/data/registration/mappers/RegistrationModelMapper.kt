package org.threehundredtutor.data.registration.mappers

import org.threehundredtutor.data.core.models.ErrorType
import org.threehundredtutor.data.registration.models.RegisterResponse
import org.threehundredtutor.domain.registration.models.RegisteredUserModel
import org.threehundredtutor.domain.registration.models.RegistrationModel

fun RegisterResponse.toRegistrationModelMapper(): RegistrationModel {
    return RegistrationModel(
        succeded = succeded ?: false,
        errorMessage = errorMessage.orEmpty(),
        errorType = errorType ?: ErrorType.NONE,
        registeredUser = RegisteredUserModel(
            id = registeredUser?.id.orEmpty(),
            phoneNumber = registeredUser?.phoneNumber.orEmpty(),
            email = registeredUser?.email.orEmpty()
        )
    )
}
