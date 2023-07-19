package org.threehundredtutor.data.registration

import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.data.registration.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST("account/Register")
    suspend fun register(@Body params: RegisterParams): Response<RegisterResponse>
}
