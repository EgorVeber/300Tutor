package com.v_points.app.domain.registration.usecases

import com.v_points.app.domain.registration.models.RegistrationParamsModel
import com.v_points.app.domain.registration.models.RegistrationStudentAndSignInModel
import com.v_points.app.domain.registration.repository.RegistrationRepository
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
