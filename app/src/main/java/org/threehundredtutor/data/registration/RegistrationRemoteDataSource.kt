package org.threehundredtutor.data.registration

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.registration.models.AccountRegisterAndSignInResponse
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.data.registration.models.StudentRegisterAndSignIn
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service: RegistrationService =
        serviceGeneratorProvider.getService(RegistrationService::class)

    suspend fun registerAccountAndSignIn(registerParams: RegisterParams): AccountRegisterAndSignInResponse =
        service.registerAccountAndSignIn(registerParams)

    suspend fun registerStudentAndSignIn(registerParams: RegisterParams): StudentRegisterAndSignIn =
        service.registerStudentAndSignIn(registerParams)
}
