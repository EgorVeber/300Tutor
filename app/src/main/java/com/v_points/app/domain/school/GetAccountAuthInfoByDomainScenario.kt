package com.v_points.app.domain.school

import com.v_points.app.domain.common.AuthorizationInfoAndDomainModel
import com.v_points.app.domain.common.GetAccountAuthInfoUseCase
import javax.inject.Inject

class GetAccountAuthInfoByDomainScenario @Inject constructor(
    private val getAccountAuthInfoUseCase: GetAccountAuthInfoUseCase,
) {
    operator fun invoke(domain: String): AuthorizationInfoAndDomainModel? {
        return getAccountAuthInfoUseCase().firstOrNull { it.domain == domain }
    }
}