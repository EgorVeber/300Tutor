package org.v_points.data.account

import org.v_points.data.account.AccountApi.ACCOUNT_LINK_CREATE_FROM_AUTHENTICATION
import org.v_points.data.account.AccountApi.ACCOUNT_LOGOUT
import org.v_points.data.account.AccountApi.ACCOUNT_USER
import org.v_points.data.account.models.AccountResponse
import org.v_points.data.account.models.AuthenticationRequest
import org.v_points.data.account.models.CreateLoginLinkResultResponse
import org.v_points.data.account.models.LogoutResponse
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