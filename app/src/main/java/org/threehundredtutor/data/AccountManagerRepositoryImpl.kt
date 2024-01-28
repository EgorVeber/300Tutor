package org.threehundredtutor.data

import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.domain.AccountManagerRepository
import javax.inject.Inject

class AccountManagerRepositoryImpl @Inject constructor(
    private val accountManager: AccountManager
) : AccountManagerRepository {
    override fun setAccountInfo(login: String, password: String) {
        accountManager.setAccountInfo(login, password)
    }

    override fun getAccountInfo(): Pair<String, String> = accountManager.getAccountInfo()
}