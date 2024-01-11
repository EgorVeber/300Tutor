package org.threehundredtutor.data.account

import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.account.models.AccountResponse
import org.threehundredtutor.data.account.models.AuthenticationRequest
import org.threehundredtutor.data.account.models.CreateLoginLinkResultResponse
import org.threehundredtutor.data.account.models.LogoutResponse
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: AccountService = serviceGeneratorProvider.getService(AccountService::class)

    suspend fun getAccount(): AccountResponse = service.getAccount()

    suspend fun logout(): LogoutResponse = service.logout()

    suspend fun createAuthentication(): CreateLoginLinkResultResponse =
        service.createAuthentication(AuthenticationRequest(true, EMPTY_STRING))
}