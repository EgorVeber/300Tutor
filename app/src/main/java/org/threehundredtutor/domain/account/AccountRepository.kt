package org.threehundredtutor.domain.account

import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.models.CreateLoginLinkResultModel
import org.threehundredtutor.domain.account.models.LogoutModel

interface AccountRepository {
    suspend fun getAccount(): AccountModel
    suspend fun logout(): LogoutModel
    suspend fun createAuthentication(): CreateLoginLinkResultModel
}
