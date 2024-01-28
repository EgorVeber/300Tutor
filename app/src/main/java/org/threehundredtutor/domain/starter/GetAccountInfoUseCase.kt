package org.threehundredtutor.domain.starter

import org.threehundredtutor.domain.AccountManagerRepository
import javax.inject.Inject

class GetAccountInfoUseCase @Inject constructor(
    private val accountManagerRepository: AccountManagerRepository
) {
     operator fun invoke(): Pair<String, String> = accountManagerRepository.getAccountInfo()
}
