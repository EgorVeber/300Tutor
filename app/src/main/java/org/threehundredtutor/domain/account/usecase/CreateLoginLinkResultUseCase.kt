package org.threehundredtutor.domain.account.usecase

import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.CreateLoginLinkResultModel
import javax.inject.Inject

class CreateLoginLinkResultUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): CreateLoginLinkResultModel =
        accountRepository.createAuthentication()
}