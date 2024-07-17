package org.threehundredtutor.domain.authorization

interface AuthorizationRepository {
    suspend fun login(loginParamsModel: LoginParamsModel): LoginModel
}
