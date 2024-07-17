package org.threehundredtutor.domain.authorization

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(loginParamsModel: LoginParamsModel): LoginModel =
        repository.login(loginParamsModel)
}