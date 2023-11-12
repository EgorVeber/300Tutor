package org.threehundredtutor.data.registration


import org.threehundredtutor.data.registration.mappers.toAccountRegisterAndSignInResponseMapper
import org.threehundredtutor.data.registration.mappers.toRegistrationStudentAndSignInModel
import org.threehundredtutor.data.registration.models.RegisteRequest
import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationParamsModel
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource,
) : RegistrationRepository {

    override suspend fun registerAccount(params: RegistrationParamsModel): RegistrationAccountAndSignInModel {
        val registeRequest = RegisteRequest(
            email = params.email,
            noEmail = params.noEmail,
            name = params.name,
            surname = params.surname,
            patronymic = params.patronymic,
            phoneNumber = params.phoneNumber,
            noPhoneNumber = params.noPhoneNumber,
            password = params.password
        )
        val response = registrationRemoteDataSource.registerAccountAndSignIn(registeRequest)

        return response.toAccountRegisterAndSignInResponseMapper()
    }

    override suspend fun registerStudent(params: RegistrationParamsModel): RegistrationStudentAndSignInModel {
        val registeRequest = RegisteRequest(
            email = params.email,
            noEmail = params.noEmail,
            name = params.name,
            surname = params.surname,
            patronymic = params.patronymic,
            phoneNumber = params.phoneNumber,
            noPhoneNumber = params.noPhoneNumber,
            password = params.password
        )
        val response = registrationRemoteDataSource.registerStudentAndSignIn(registeRequest)

        return response.toRegistrationStudentAndSignInModel()
    }
}
