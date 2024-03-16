package org.threehundredtutor.data.common

import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.domain.common.AccountManagerRepository
import javax.inject.Inject

class AccountManagerRepositoryImpl @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountManagerRepository {
    override fun setAccountInfo(login: String, password: String, userId: String) {
        accountLocalDataSource.setAccountInfo(login, password, userId)
    }

    override fun getAccountInfo(): Triple<String, String, String> = accountLocalDataSource.getAccountInfo()
}