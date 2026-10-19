package com.v_points.app.data.account

import com.v_points.app.data.account.mappers.toAccountModel
import com.v_points.app.data.account.mappers.toCreateLoginLinkResultModel
import com.v_points.app.data.account.mappers.toLogoutModel
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.account.models.AccountModel
import com.v_points.app.domain.account.models.LogoutModel
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