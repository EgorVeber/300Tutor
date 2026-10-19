package com.v_points.app.data.authorization

import com.v_points.app.data.authorization.AuthorizationApi.ACCOUNT_LOGIN
import com.v_points.app.data.authorization.models.LoginDateRequest
import com.v_points.app.data.authorization.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST(ACCOUNT_LOGIN)
    suspend fun login(@Body params: LoginDateRequest): LoginResponse
}
