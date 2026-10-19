package org.v_points.domain.starter

import org.v_points.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class GetAccountAuthorizationInfoUseCase @Inject constructor(
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    operator fun invoke(): Pair<String, String> =
        accountAuthorizationInfoRepository.getAccountAuthorizationInfo()
}