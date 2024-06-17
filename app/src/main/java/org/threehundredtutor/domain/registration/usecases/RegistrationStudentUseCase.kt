package org.threehundredtutor.domain.registration.usecases

import org.threehundredtutor.domain.registration.models.RegistrationParamsModel
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationStudentUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {

    suspend operator fun invoke(
        email: String,
        name: String,
        surname: String,
        phoneNumber: String,
        password: String
    ): RegistrationStudentAndSignInModel {
        val registrationParamsModel = RegistrationParamsModel(
            email = email,
            noEmail = email.isEmpty(),
            name = name,
            surname = surname,
            phoneNumber = phoneNumber,
            noPhoneNumber = phoneNumber.isEmpty(),
            password = password
        )
        return repository.registerStudent(registrationParamsModel)
    }
}
