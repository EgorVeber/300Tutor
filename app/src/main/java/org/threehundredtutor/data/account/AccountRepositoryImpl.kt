package org.threehundredtutor.data.account

import org.threehundredtutor.common.utils.PrefsCookie
import org.threehundredtutor.domain.account.AccountModel
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.LogoutModel
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val prefsCookie: PrefsCookie
) : AccountRepository {
    override suspend fun getAccount(): AccountModel =
        accountRemoteDataSource.getAccount().toAccountModel()

    override suspend fun logout(): LogoutModel {
        clearCookie()
        return accountRemoteDataSource.logout().toLogoutModel()
    }

    override suspend fun clearCookie() {
        prefsCookie.clear()
    }
}
