package org.threehundredtutor.domain.account

import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(): LogoutModel = repository.logout()
}
