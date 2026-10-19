package com.v_points.app.data.registration

import com.v_points.app.data.registration.mappers.toRegistrationStudentAndSignInModel
import com.v_points.app.data.registration.models.RegisteRequest
import com.v_points.app.domain.registration.models.RegistrationParamsModel
import com.v_points.app.domain.registration.models.RegistrationStudentAndSignInModel
import com.v_points.app.domain.registration.repository.RegistrationRepository
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