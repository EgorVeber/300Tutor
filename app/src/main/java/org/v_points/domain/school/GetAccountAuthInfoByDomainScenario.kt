package org.v_points.domain.school

import org.v_points.domain.common.AuthorizationInfoAndDomainModel
import org.v_points.domain.common.GetAccountAuthInfoUseCase
import javax.inject.Inject

class GetAccountAuthInfoByDomainScenario @Inject constructor(
    private val getAccountAuthInfoUseCase: GetAccountAuthInfoUseCase,
) {
    operator fun invoke(domain: String): AuthorizationInfoAndDomainModel? {
        return getAccountAuthInfoUseCase().firstOrNull { it.domain == domain }
    }
}