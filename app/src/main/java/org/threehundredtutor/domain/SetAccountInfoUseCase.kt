package org.threehundredtutor.domain

import javax.inject.Inject

class SetAccountInfoUseCase @Inject constructor(
    private val accountManagerRepository: AccountManagerRepository
) {
    operator fun invoke(login: String, password: String) {
        accountManagerRepository.setAccountInfo(login, password)
    }
}
