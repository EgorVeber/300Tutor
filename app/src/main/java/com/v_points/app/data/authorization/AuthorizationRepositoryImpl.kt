package com.v_points.app.data.authorization

import com.v_points.app.data.authorization.mapper.toLoginDateRequest
import com.v_points.app.data.authorization.mapper.toLoginModel
import com.v_points.app.domain.authorization.AuthorizationRepository
import com.v_points.app.domain.authorization.LoginModel
import com.v_points.app.domain.authorization.LoginParamsModel
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationDataSource: AuthorizationDataSource,
) : AuthorizationRepository {
    override suspend fun login(loginParamsModel: LoginParamsModel): LoginModel =
        authorizationDataSource.login(loginParamsModel.toLoginDateRequest()).toLoginModel()
}