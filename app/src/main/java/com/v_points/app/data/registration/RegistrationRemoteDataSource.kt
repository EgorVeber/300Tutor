package com.v_points.app.data.registration

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.registration.models.RegisteRequest
import com.v_points.app.data.registration.models.StudentRegisterAndSignInResponse
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service = { serviceGeneratorProvider.getService(RegistrationService::class) }

    suspend fun registerStudentAndSignIn(registeRequest: RegisteRequest): StudentRegisterAndSignInResponse =
        service().registerStudentAndSignIn(registeRequest)
}
