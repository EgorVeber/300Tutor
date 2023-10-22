package org.threehundredtutor.domain.registration.usecases

import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationParamsModel
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationAccountUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {

    suspend operator fun invoke(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ): RegistrationAccountAndSignInModel {
        val registrationParamsModel = RegistrationParamsModel(
            email = email,
            noEmail = email.isEmpty(),
            name = name,
            surname = surname,
            patronymic = patronymic,
            phoneNumber = phoneNumber,
            noPhoneNumber = phoneNumber.isEmpty(),
            password = password
        )
        return repository.registerAccount(registrationParamsModel)
    }
}
