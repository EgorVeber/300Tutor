package org.v_points.data.registration

import org.v_points.data.registration.mappers.toRegistrationStudentAndSignInModel
import org.v_points.data.registration.models.RegisteRequest
import org.v_points.domain.registration.models.RegistrationParamsModel
import org.v_points.domain.registration.models.RegistrationStudentAndSignInModel
import org.v_points.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource,
) : RegistrationRepository {

    override suspend fun registerStudent(params: RegistrationParamsModel): RegistrationStudentAndSignInModel {
        val registeRequest = RegisteRequest(
            email = params.email,
            noEmail = params.noEmail,
            name = params.name,
            surname = params.surname,
            phoneNumber = params.phoneNumber,
            noPhoneNumber = params.noPhoneNumber,
            password = params.password
        )
        val response = registrationRemoteDataSource.registerStudentAndSignIn(registeRequest)

        return response.toRegistrationStudentAndSignInModel()
    }
}