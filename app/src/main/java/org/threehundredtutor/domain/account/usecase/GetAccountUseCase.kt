package org.threehundredtutor.domain.account.usecase

import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.account.models.AccountModel
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): AccountModel = accountRepository.getAccount()
}
