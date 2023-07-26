package org.threehundredtutor.domain.authorization

interface AuthorizationRepository {

   suspend fun login(loginDateModel: LoginDateModel): LoginModel
}
