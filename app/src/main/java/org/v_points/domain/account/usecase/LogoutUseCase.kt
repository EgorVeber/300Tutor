package org.v_points.domain.account.usecase

import org.v_points.domain.account.AccountRepository
import org.v_points.domain.account.models.LogoutModel
import org.v_points.domain.common.AccountAuthorizationInfoRepository
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
