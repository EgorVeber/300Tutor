package org.threehundredtutor.data.registration


import org.threehundredtutor.data.registration.mappers.toRegistrationModelMapper
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource
) : RegistrationRepository {

    override suspend fun register(params: RegistrationParams): RegistrationModel {
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
        val response = registrationRemoteDataSource.register(registerParams)

        return response.toRegistrationModelMapper()
    }
}
