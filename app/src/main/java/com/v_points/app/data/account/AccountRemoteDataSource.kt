package com.v_points.app.data.account

import com.v_points.app.data.account.models.AccountResponse
import com.v_points.app.data.account.models.AuthenticationRequest
import com.v_points.app.data.account.models.CreateLoginLinkResultResponse
import com.v_points.app.data.account.models.LogoutResponse
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service: () -> AccountService = {
        serviceGeneratorProvider.getService(AccountService::class)
    }

    suspend fun getAccount(): AccountResponse = service().getAccount()

    suspend fun logout(): LogoutResponse = service().logout()

    suspend fun createAuthentication(redirectLink: String): CreateLoginLinkResultResponse =
        service().createAuthentication(AuthenticationRequest(true, redirectLink))
}