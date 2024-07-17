package org.threehundredtutor.data.common

import org.threehundredtutor.data.common.data_source.DomainLocalDataSource
import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import org.threehundredtutor.domain.common.AuthorizationInfoAndDomainModel
import javax.inject.Inject

class AccountAuthorizationInfoRepositoryImpl @Inject constructor(
    private val privateDataSource: PrivateDataSource,
    private val domainLocalDataSource: DomainLocalDataSource,
) : AccountAuthorizationInfoRepository {
    override fun setAccountAuthorizationInfo(login: String, password: String) {
        privateDataSource.setAccountAuthorizationInfo(login, password)

        val currentSchoolDomain = domainLocalDataSource.getDomain()

        val accountsNotCurrentDomain =
            privateDataSource.getAccountsAuthInfo().filter {
                it.domain != currentSchoolDomain
            }

        privateDataSource.setAuthDomainInfo(
            accountsNotCurrentDomain + listOf(
                AuthorizationInfoAndDomainModel(
                    login = login,
                    password = password,
                    domain = currentSchoolDomain
                )
            )
        )
    }

    override fun getAccountAuthorizationInfo(): Pair<String, String> {
        return privateDataSource.getAccountAuthorizationInfo()
    }

    override fun getAccountsAuthInfo(): List<AuthorizationInfoAndDomainModel> {
        return privateDataSource.getAccountsAuthInfo()
    }

    override fun clearAuthorizationInfo() {
        privateDataSource.clearAuthorizationInfo()
    }
}