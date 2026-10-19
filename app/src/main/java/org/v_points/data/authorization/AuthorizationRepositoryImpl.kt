package org.v_points.data.authorization

import org.v_points.data.authorization.mapper.toLoginDateRequest
import org.v_points.data.authorization.mapper.toLoginModel
import org.v_points.domain.authorization.AuthorizationRepository
import org.v_points.domain.authorization.LoginModel
import org.v_points.domain.authorization.LoginParamsModel
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationDataSource: AuthorizationDataSource,
) : AuthorizationRepository {
    override suspend fun login(loginParamsModel: LoginParamsModel): LoginModel =
        authorizationDataSource.login(loginParamsModel.toLoginDateRequest()).toLoginModel()
}