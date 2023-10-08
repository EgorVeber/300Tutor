package org.threehundredtutor.data.registration

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.data.registration.models.RegisterResponse
import javax.inject.Inject

class RegistrationRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service: RegistrationService =
        serviceGeneratorProvider.getService(RegistrationService::class)

    suspend fun register(registerParams: RegisterParams): RegisterResponse =
        service.register(registerParams)
}
