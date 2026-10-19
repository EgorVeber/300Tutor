package org.v_points.data.authorization

import org.v_points.data.authorization.models.LoginDateRequest
import org.v_points.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class AuthorizationDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> AuthorizationService = {
        serviceGeneratorProvider.getService(AuthorizationService::class)
    }

    suspend fun login(loginDateModel: LoginDateRequest) = service().login(loginDateModel)
}