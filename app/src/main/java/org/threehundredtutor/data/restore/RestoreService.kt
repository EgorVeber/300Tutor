package org.threehundredtutor.data.restore

import org.threehundredtutor.data.restore.RestoreApi.ACCOUNT_PASSWORD_FORGOT_START
import retrofit2.http.Body
import retrofit2.http.POST

interface RestoreService {
    @POST(ACCOUNT_PASSWORD_FORGOT_START)
    suspend fun forgotStart(@Body params: RestoreRequest): RestoreResponse
}
