package org.threehundredtutor.data.registration


import org.threehundredtutor.data.authorization.mapper.toLoginModel
import org.threehundredtutor.data.registration.mappers.toRegistrationModelMapper
import org.threehundredtutor.data.registration.mappers.toRegistrationStudentAndSignInModel
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationParams
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource
) : RegistrationRepository {

    override suspend fun registerAccount(params: RegistrationParams): RegistrationAccountAndSignInModel {
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
        val response = registrationRemoteDataSource.registerAccountAndSignIn(registerParams)

        return RegistrationAccountAndSignInModel(
            response.registerResponse.toRegistrationModelMapper(),
            response.loginResponse.toLoginModel()
        )
    }

    override suspend fun registerStudent(params: RegistrationParams): RegistrationStudentAndSignInModel {
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
        val response = registrationRemoteDataSource.registerStudentAndSignIn(registerParams)

        return response.toRegistrationStudentAndSignInModel()
    }
}
