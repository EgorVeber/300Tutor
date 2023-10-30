package org.threehundredtutor.data.registration

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.registration.models.AccountRegisterAndSignInResponse
import org.threehundredtutor.data.registration.models.RegisteRequest
import org.threehundredtutor.data.registration.models.StudentRegisterAndSignInResponse
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service: RegistrationService =
        serviceGeneratorProvider.getService(RegistrationService::class)

    suspend fun registerAccountAndSignIn(registeRequest: RegisteRequest): AccountRegisterAndSignInResponse =
        service.registerAccountAndSignIn(registeRequest)

    suspend fun registerStudentAndSignIn(registeRequest: RegisteRequest): StudentRegisterAndSignInResponse =
        service.registerStudentAndSignIn(registeRequest)
}
