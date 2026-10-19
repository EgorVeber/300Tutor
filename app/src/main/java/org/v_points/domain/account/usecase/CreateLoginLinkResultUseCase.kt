package org.v_points.domain.account.usecase

import org.v_points.domain.account.AccountRepository
import org.v_points.domain.account.models.CreateLoginLinkResultModel
import javax.inject.Inject

class CreateLoginLinkResultUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(siteUrl: String, redirectLink: String): CreateLoginLinkResultModel =
        accountRepository.createAuthentication(siteUrl, redirectLink)
}
