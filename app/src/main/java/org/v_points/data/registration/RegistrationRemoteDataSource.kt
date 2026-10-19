package org.v_points.data.registration

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.registration.models.RegisteRequest
import org.v_points.data.registration.models.StudentRegisterAndSignInResponse
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service = { serviceGeneratorProvider.getService(RegistrationService::class) }

    suspend fun registerStudentAndSignIn(registeRequest: RegisteRequest): StudentRegisterAndSignInResponse =
        service().registerStudentAndSignIn(registeRequest)
}
