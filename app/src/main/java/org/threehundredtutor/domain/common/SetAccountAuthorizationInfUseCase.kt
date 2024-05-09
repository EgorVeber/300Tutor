package org.threehundredtutor.domain.common

import javax.inject.Inject

class SetAccountAuthorizationInfUseCase @Inject constructor(
    private val accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository
) {
    operator fun invoke(login: String, password: String) {
        accountAuthorizationInfoRepository.setAccountAuthorizationInfo(login = login, password = password)
    }
}