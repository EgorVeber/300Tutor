package org.threehundredtutor.data.registration

import org.threehundredtutor.data.registration.mappers.toRegistrationModelMapper
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.di.modules.NetworkModule
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams

class RegistrationRepositoryImpl {

    private val registrationService: RegistrationService =
        NetworkModule().createRegistrationService()

    suspend fun registerUser(params: RegistrationParams): RegistrationModel {
        val registerParams = RegisterParams(
            email = params.email,
            noEmail = params.noEmail,
            name = params.name,
            surname = params.surname,
            patronymic = params.patronymic,
            phoneNumber = params.phoneNumber,
            noPhoneNumber = params.noPhoneNumber,
            password = params.password
        )
        val response = registrationService.register(registerParams)

        return response.toRegistrationModelMapper()
    }
}
