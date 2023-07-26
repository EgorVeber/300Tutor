package org.threehundredtutor.data.authorization

import org.threehundredtutor.data.authorization.AuthorizationApi.ACCOUNT_LOGIN
import org.threehundredtutor.data.authorization.models.LoginDateRequest
import org.threehundredtutor.data.authorization.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST(ACCOUNT_LOGIN)
    suspend fun login(@Body params: LoginDateRequest): LoginResponse
}
