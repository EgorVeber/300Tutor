package org.threehundredtutor.data.common

import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class AccountAuthorizationInfoRepositoryImpl @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountAuthorizationInfoRepository {
    override fun setAccountAuthorizationInfo(login: String, password: String) {
        accountLocalDataSource.setAccountAuthorizationInfo(login, password)
    }

    override fun getAccountAuthorizationInfo(): Pair<String, String> =
        accountLocalDataSource.getAccountAuthorizationInfo()
}