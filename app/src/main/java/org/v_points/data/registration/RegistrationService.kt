package org.v_points.data.registration

import org.v_points.data.registration.RegistrationApi.STUDENT_REGISTRATION
import org.v_points.data.registration.models.RegisteRequest
import org.v_points.data.registration.models.StudentRegisterAndSignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST(STUDENT_REGISTRATION)
    suspend fun registerStudentAndSignIn(@Body params: RegisteRequest): StudentRegisterAndSignInResponse
}
