package org.threehundredtutor.data.registration

import org.threehundredtutor.data.registration.RegistrationApi.ACCOUNT_REGISTRATION
import org.threehundredtutor.data.registration.RegistrationApi.STUDENT_REGISTRATION
import org.threehundredtutor.data.registration.models.AccountRegisterAndSignInResponse
import org.threehundredtutor.data.registration.models.RegisteRequest
import org.threehundredtutor.data.registration.models.StudentRegisterAndSignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST(ACCOUNT_REGISTRATION)
    suspend fun registerAccountAndSignIn(@Body params: RegisteRequest): AccountRegisterAndSignInResponse

    @POST(STUDENT_REGISTRATION)
    suspend fun registerStudentAndSignIn(@Body params: RegisteRequest): StudentRegisterAndSignInResponse
}
