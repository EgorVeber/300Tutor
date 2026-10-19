package com.v_points.app.data.account

import com.v_points.app.data.account.AccountApi.ACCOUNT_LINK_CREATE_FROM_AUTHENTICATION
import com.v_points.app.data.account.AccountApi.ACCOUNT_LOGOUT
import com.v_points.app.data.account.AccountApi.ACCOUNT_USER
import com.v_points.app.data.account.models.AccountResponse
import com.v_points.app.data.account.models.AuthenticationRequest
import com.v_points.app.data.account.models.CreateLoginLinkResultResponse
import com.v_points.app.data.account.models.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService {
    @GET(ACCOUNT_USER)
    suspend fun getAccount(): AccountResponse

    @POST(ACCOUNT_LOGOUT)
    suspend fun logout(): LogoutResponse

    @POST(ACCOUNT_LINK_CREATE_FROM_AUTHENTICATION)
    suspend fun createAuthentication(@Body params: AuthenticationRequest): CreateLoginLinkResultResponse
}