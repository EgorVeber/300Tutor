package org.threehundredtutor.domain.authorization

import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl
import org.threehundredtutor.data.registration.RegistrationRepositoryImpl
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams

class LoginUseCase(private val repository: AuthorizationRepository = AuthorizationRepositoryImpl()) {

    suspend operator fun invoke(loginDateModel: LoginDateModel): LoginModel =
        repository.login(loginDateModel)
}
