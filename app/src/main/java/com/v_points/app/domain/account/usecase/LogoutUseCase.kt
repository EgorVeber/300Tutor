package com.v_points.app.domain.account.usecase

import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.account.models.LogoutModel
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
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
