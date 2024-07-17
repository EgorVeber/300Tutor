package org.threehundredtutor.domain.school

import org.threehundredtutor.domain.common.AuthorizationInfoAndDomainModel
import org.threehundredtutor.domain.common.GetAccountAuthInfoUseCase
import javax.inject.Inject

class GetAccountAuthInfoByDomainScenario @Inject constructor(
    private val getAccountAuthInfoUseCase: GetAccountAuthInfoUseCase,
) {
    operator fun invoke(domain: String): AuthorizationInfoAndDomainModel? {
        return getAccountAuthInfoUseCase().firstOrNull { it.domain == domain }
    }
}