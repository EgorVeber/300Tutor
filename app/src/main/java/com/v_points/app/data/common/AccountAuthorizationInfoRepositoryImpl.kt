package com.v_points.app.data.common

import com.v_points.app.data.common.data_source.DomainLocalDataSource
import com.v_points.app.data.common.data_source.PrivateDataSource
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.domain.common.AuthorizationInfoAndDomainModel
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