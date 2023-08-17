package org.threehundredtutor.data.account

import org.threehundredtutor.data.account.AccountApi.ACCOUNT_LOGOUT
import org.threehundredtutor.data.account.AccountApi.ACCOUNT_USER
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService {
    @GET(ACCOUNT_USER)
    suspend fun getAccount(): AccountResponse

    @POST(ACCOUNT_LOGOUT)
    suspend fun logout(): LogoutResponse
}
