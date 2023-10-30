package org.threehundredtutor.data.account

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: AccountService = serviceGeneratorProvider.getService(AccountService::class)

    suspend fun getAccount(): AccountResponse = service.getAccount()

    suspend fun logout(): LogoutResponse = service.logout()
}