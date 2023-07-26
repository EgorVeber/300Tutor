package org.threehundredtutor.data.authorization

import org.threehundredtutor.data.authorization.mapper.toLoginDateRequest
import org.threehundredtutor.data.authorization.mapper.toLoginModel
import org.threehundredtutor.di.modules.NetworkModule
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.domain.authorization.LoginDateModel
import org.threehundredtutor.domain.authorization.LoginModel

class AuthorizationRepositoryImpl(
    private val authorizationService: AuthorizationService = NetworkModule().createAuthorizationService(),
) : AuthorizationRepository {
    override suspend fun login(loginDateModel: LoginDateModel): LoginModel =
        authorizationService.login(loginDateModel.toLoginDateRequest()).toLoginModel()
}
