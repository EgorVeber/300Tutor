package org.v_points.domain.common

import javax.inject.Inject

class GetAccountAuthInfoUseCase @Inject constructor(
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    operator fun invoke(): List<AuthorizationInfoAndDomainModel> = accountAuthorizationInfoRepository.getAccountsAuthInfo()
}