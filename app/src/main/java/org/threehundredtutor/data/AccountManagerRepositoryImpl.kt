package org.threehundredtutor.data

import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.domain.AccountManagerRepository
import javax.inject.Inject

class AccountManagerRepositoryImpl @Inject constructor(
    private val accountManager: AccountManager
) : AccountManagerRepository {
    override fun setAccountInfo(login: String, password: String, userId: String) {
        accountManager.setAccountInfo(login, password, userId)
    }

    override fun getAccountInfo(): Triple<String, String, String> = accountManager.getAccountInfo()
}