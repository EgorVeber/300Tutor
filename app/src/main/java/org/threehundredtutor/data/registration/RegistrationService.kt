package org.threehundredtutor.data.registration

import org.threehundredtutor.data.registration.models.AccountRegisterAndSignInResponse
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.data.registration.models.StudentRegisterAndSignIn
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST("account/registerAndSignIn")
    suspend fun registerAccountAndSignIn(@Body params: RegisterParams): AccountRegisterAndSignInResponse

    @POST("tutor/student/registerAndSignIn")
    suspend fun registerStudentAndSignIn(@Body params: RegisterParams): StudentRegisterAndSignIn
}
