package org.threehundredtutor.domain.common

interface AccountAuthorizationInfoRepository {
    fun setAccountAuthorizationInfo(login: String, password: String)
    fun getAccountAuthorizationInfo(): Pair<String, String>
    fun clearAuthorizationInfo()
    fun getAccountsAuthInfo(): List<AuthorizationInfoAndDomainModel>
}