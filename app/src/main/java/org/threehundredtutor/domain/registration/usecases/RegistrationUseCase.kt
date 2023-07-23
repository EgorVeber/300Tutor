package org.threehundredtutor.domain.registration.usecases

import org.threehundredtutor.data.registration.RegistrationRepositoryImpl
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams

class RegistrationUseCase {

    suspend operator fun invoke(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ): RegistrationModel {
        val registrationParams = RegistrationParams(
            email = email,
            noEmail = email.isEmpty(),
            name = name,
            surname = surname,
            patronymic = patronymic,
            phoneNumber = phoneNumber,
            noPhoneNumber = phoneNumber.isEmpty(),
            password = password
        )
        val repository = RegistrationRepositoryImpl()
        return repository.registerUser(registrationParams)
    }
}
