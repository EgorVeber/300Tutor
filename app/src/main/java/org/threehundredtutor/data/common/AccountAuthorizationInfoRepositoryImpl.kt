package org.threehundredtutor.data.common

import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class AccountAuthorizationInfoRepositoryImpl @Inject constructor(
    private val privateDataSource: PrivateDataSource,
) : AccountAuthorizationInfoRepository {
    override fun setAccountAuthorizationInfo(login: String, password: String) {
        privateDataSource.setAccountAuthorizationInfo(login, password)
    }

    override fun getAccountAuthorizationInfo(): Pair<String, String> =
        privateDataSource.getAccountAuthorizationInfo()

    override fun clearAuthorizationInfo() {
        privateDataSource.clearAuthorizationInfo()
    }
}