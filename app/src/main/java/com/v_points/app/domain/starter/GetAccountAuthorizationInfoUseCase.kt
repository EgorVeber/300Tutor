package com.v_points.app.domain.starter

import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class GetAccountAuthorizationInfoUseCase @Inject constructor(
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    operator fun invoke(): Pair<String, String> =
        accountAuthorizationInfoRepository.getAccountAuthorizationInfo()
}