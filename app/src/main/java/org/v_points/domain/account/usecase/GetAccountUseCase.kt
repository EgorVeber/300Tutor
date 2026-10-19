package org.v_points.domain.account.usecase

import org.v_points.domain.account.AccountRepository
import org.v_points.domain.account.models.AccountModel
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(force: Boolean): AccountModel = accountRepository.getAccount(force)
}
