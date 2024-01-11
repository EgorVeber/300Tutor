package org.threehundredtutor.domain.account.usecase

import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.LogoutModel
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(): LogoutModel = repository.logout()
}
