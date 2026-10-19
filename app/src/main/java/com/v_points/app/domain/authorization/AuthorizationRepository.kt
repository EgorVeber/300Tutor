package com.v_points.app.domain.authorization

interface AuthorizationRepository {
    suspend fun login(loginParamsModel: LoginParamsModel): LoginModel
}
