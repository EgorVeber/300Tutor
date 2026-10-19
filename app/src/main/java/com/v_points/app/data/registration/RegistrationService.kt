package com.v_points.app.data.registration

import com.v_points.app.data.registration.RegistrationApi.STUDENT_REGISTRATION
import com.v_points.app.data.registration.models.RegisteRequest
import com.v_points.app.data.registration.models.StudentRegisterAndSignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST(STUDENT_REGISTRATION)
    suspend fun registerStudentAndSignIn(@Body params: RegisteRequest): StudentRegisterAndSignInResponse
}
