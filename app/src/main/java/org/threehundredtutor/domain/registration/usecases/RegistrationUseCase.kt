package org.threehundredtutor.domain.registration.usecases

import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {

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
        return repository.register(registrationParams)
    }
}
