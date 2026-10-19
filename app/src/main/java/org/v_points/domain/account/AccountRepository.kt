package org.v_points.domain.account

import org.v_points.domain.account.models.AccountModel
import org.v_points.domain.account.models.CreateLoginLinkResultModel
import org.v_points.domain.account.models.LogoutModel

interface AccountRepository {
    suspend fun getAccount(force: Boolean): AccountModel
    suspend fun logout(): LogoutModel
    suspend fun createAuthentication(
        siteUrl: String,
        redirectLink: String
    ): CreateLoginLinkResultModel
}