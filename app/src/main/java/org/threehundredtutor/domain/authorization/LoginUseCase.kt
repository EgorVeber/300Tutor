package org.threehundredtutor.domain.authorization

import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl

class LoginUseCase(private val repository: AuthorizationRepository = AuthorizationRepositoryImpl()) {

    suspend operator fun invoke(loginDateModel: LoginDateModel): LoginModel =
        repository.login(loginDateModel)
}
