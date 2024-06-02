package org.threehundredtutor.domain.account.usecase

import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.LogoutModel
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    suspend operator fun invoke(): LogoutModel =
        accountRepository.logout().apply {
            accountAuthorizationInfoRepository.clearAuthorizationInfo()
        }
}
