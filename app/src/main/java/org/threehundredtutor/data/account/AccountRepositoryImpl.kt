package org.threehundredtutor.data.account

import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.data.account.mappers.toAccountModel
import org.threehundredtutor.data.account.mappers.toCreateLoginLinkResultModel
import org.threehundredtutor.data.account.mappers.toLogoutModel
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.models.LogoutModel
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountManager: AccountManager
) : AccountRepository {
    override suspend fun getAccount(): AccountModel =
        accountRemoteDataSource.getAccount().toAccountModel()

    override suspend fun logout(): LogoutModel {
        return accountRemoteDataSource.logout().toLogoutModel().apply {
            accountManager.clearAccount()
            accountManager.clearCookie()
        }
    }

    override suspend fun createAuthentication() =
        accountRemoteDataSource.createAuthentication().toCreateLoginLinkResultModel()
}
