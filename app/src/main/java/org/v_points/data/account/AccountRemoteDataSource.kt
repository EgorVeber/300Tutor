package org.v_points.data.account

import org.v_points.data.account.models.AccountResponse
import org.v_points.data.account.models.AuthenticationRequest
import org.v_points.data.account.models.CreateLoginLinkResultResponse
import org.v_points.data.account.models.LogoutResponse
import org.v_points.data.common.network.ServiceGeneratorProvider
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