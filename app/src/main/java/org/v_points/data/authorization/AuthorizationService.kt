package org.v_points.data.authorization

import org.v_points.data.authorization.AuthorizationApi.ACCOUNT_LOGIN
import org.v_points.data.authorization.models.LoginDateRequest
import org.v_points.data.authorization.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST(ACCOUNT_LOGIN)
    suspend fun login(@Body params: LoginDateRequest): LoginResponse
}
