package org.threehundredtutor.data.authorization

import org.threehundredtutor.data.authorization.mapper.toLoginDateRequest
import org.threehundredtutor.data.authorization.mapper.toLoginModel
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.domain.authorization.LoginModel
import org.threehundredtutor.domain.authorization.LoginParamsModel
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationDataSource: AuthorizationDataSource,
) : AuthorizationRepository {
    override suspend fun login(loginParamsModel: LoginParamsModel): LoginModel =
        authorizationDataSource.login(loginParamsModel.toLoginDateRequest()).toLoginModel()
}
