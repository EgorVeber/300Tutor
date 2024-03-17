package org.threehundredtutor.domain.common

import javax.inject.Inject

class SetAccountInfoUseCase @Inject constructor(
    private val accountManagerRepository: AccountManagerRepository
) {
    operator fun invoke(login: String, password: String, userId: String) {
        accountManagerRepository.setAccountInfo(login = login, password = password, userId = userId)
    }
}
