package com.v_points.app.data.authorization

import com.v_points.app.data.authorization.models.LoginDateRequest
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class AuthorizationDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> AuthorizationService = {
        serviceGeneratorProvider.getService(AuthorizationService::class)
    }

    suspend fun login(loginDateModel: LoginDateRequest) = service().login(loginDateModel)
}