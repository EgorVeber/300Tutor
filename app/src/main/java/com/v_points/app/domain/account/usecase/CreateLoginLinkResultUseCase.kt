package com.v_points.app.domain.account.usecase

import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.account.models.CreateLoginLinkResultModel
import javax.inject.Inject

class CreateLoginLinkResultUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(siteUrl: String, redirectLink: String): CreateLoginLinkResultModel =
        accountRepository.createAuthentication(siteUrl, redirectLink)
}
