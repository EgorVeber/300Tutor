package org.threehundredtutor.domain.account

interface AccountRepository {
   suspend fun getAccount(): AccountModel
   suspend fun logout(): LogoutModel
}
