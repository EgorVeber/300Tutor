package com.v_points.app.domain.account

import com.v_points.app.domain.account.models.AccountModel
import com.v_points.app.domain.account.models.CreateLoginLinkResultModel
import com.v_points.app.domain.account.models.LogoutModel

interface AccountRepository {
    suspend fun getAccount(force: Boolean): AccountModel
    suspend fun logout(): LogoutModel
    suspend fun createAuthentication(
        siteUrl: String,
        redirectLink: String
    ): CreateLoginLinkResultModel
}