package org.v_points.data.account

import org.v_points.data.account.mappers.toAccountModel
import org.v_points.data.account.mappers.toCreateLoginLinkResultModel
import org.v_points.data.account.mappers.toLogoutModel
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.account.models.AccountModel
import org.v_points.domain.account.models.LogoutModel
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountRepository {
    override suspend fun getAccount(force: Boolean): AccountModel =
        when {
            force -> getAccountAndSave()

            accountLocalDataSource.getLastUpdate() + FIVE_MINUTES < System.currentTimeMillis() -> {
                getAccountAndSave()
            }

            else -> {
                val accountCache = accountLocalDataSource.getAccount()
                if (accountCache.isEmpty()) getAccountAndSave() else accountCache
            }
        }

    private suspend fun getAccountAndSave(): AccountModel {
        accountLocalDataSource.setAccount(accountRemoteDataSource.getAccount().toAccountModel())
        accountLocalDataSource.updateTimer()
        return accountLocalDataSource.getAccount()
    }

    override suspend fun logout(): LogoutModel =
        accountRemoteDataSource.logout().toLogoutModel().apply {
            accountLocalDataSource.clearAccount()
        }

    override suspend fun createAuthentication(siteUrl: String, redirectLink: String) =
        accountRemoteDataSource.createAuthentication(redirectLink)
            .toCreateLoginLinkResultModel(siteUrl = siteUrl)

    companion object {
        private const val FIVE_MINUTES = 3000000L
    }
}