package org.threehundredtutor.data.account

import org.threehundredtutor.data.account.AccountApi.ACCOUNT_LINK_CREATE_FROM_AUTHENTICATION
import org.threehundredtutor.data.account.AccountApi.ACCOUNT_LOGOUT
import org.threehundredtutor.data.account.AccountApi.ACCOUNT_USER
import org.threehundredtutor.data.account.models.AccountResponse
import org.threehundredtutor.data.account.models.AuthenticationRequest
import org.threehundredtutor.data.account.models.CreateLoginLinkResultResponse
import org.threehundredtutor.data.account.models.LogoutResponse
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