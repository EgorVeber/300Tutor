package org.threehundredtutor.data.authorization

import org.threehundredtutor.data.authorization.models.LoginDateRequest
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class AuthorizationDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> AuthorizationService = {
        serviceGeneratorProvider.getService(AuthorizationService::class)
    }

    suspend fun login(loginDateModel: LoginDateRequest) = service().login(loginDateModel)
}