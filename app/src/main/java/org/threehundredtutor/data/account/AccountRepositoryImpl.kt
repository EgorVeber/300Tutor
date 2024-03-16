package org.threehundredtutor.data.account

import org.threehundredtutor.data.account.mappers.toAccountModel
import org.threehundredtutor.data.account.mappers.toCreateLoginLinkResultModel
import org.threehundredtutor.data.account.mappers.toLogoutModel
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.models.LogoutModel
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource,
    private val configLocalDataSource: ConfigLocalDataSource
) : AccountRepository {
    override suspend fun getAccount(): AccountModel =
        accountRemoteDataSource.getAccount().toAccountModel()

    override suspend fun logout(): LogoutModel {
        return accountRemoteDataSource.logout().toLogoutModel().apply {
            accountLocalDataSource.clearAccount()
            accountLocalDataSource.clearCookie()
        }
    }

    override suspend fun createAuthentication() =
        accountRemoteDataSource.createAuthentication()
            .toCreateLoginLinkResultModel(siteUrl = configLocalDataSource.siteUrl)
}
