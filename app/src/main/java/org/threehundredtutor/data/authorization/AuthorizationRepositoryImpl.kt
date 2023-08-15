package org.threehundredtutor.data.authorization

import org.threehundredtutor.data.authorization.mapper.toLoginDateRequest
import org.threehundredtutor.data.authorization.mapper.toLoginModel
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.domain.authorization.LoginDateModel
import org.threehundredtutor.domain.authorization.LoginModel
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationDataSource: AuthorizationDataSource,
) : AuthorizationRepository {
    override suspend fun login(loginDateModel: LoginDateModel): LoginModel =
        authorizationDataSource.login(loginDateModel.toLoginDateRequest()).toLoginModel()
}
