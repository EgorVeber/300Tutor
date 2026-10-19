package com.v_points.app.domain.account.usecase

import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.account.models.AccountModel
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(force: Boolean): AccountModel = accountRepository.getAccount(force)
}
