package org.threehundredtutor.domain.account

import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(): AccountModel = repository.getAccount()
}
