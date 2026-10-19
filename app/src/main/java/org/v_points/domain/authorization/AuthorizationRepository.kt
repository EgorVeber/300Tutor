package org.v_points.domain.authorization

interface AuthorizationRepository {
    suspend fun login(loginParamsModel: LoginParamsModel): LoginModel
}
