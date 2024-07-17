package org.threehundredtutor.domain.starter

import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Inject

class GetAccountAuthorizationInfoUseCase @Inject constructor(
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    operator fun invoke(): Pair<String, String> =
        accountAuthorizationInfoRepository.getAccountAuthorizationInfo()
}