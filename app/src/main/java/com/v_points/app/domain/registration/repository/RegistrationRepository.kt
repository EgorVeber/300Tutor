package com.v_points.app.domain.registration.repository

import com.v_points.app.domain.registration.models.RegistrationParamsModel
import com.v_points.app.domain.registration.models.RegistrationStudentAndSignInModel

interface RegistrationRepository {

    suspend fun registerStudent(params: RegistrationParamsModel): RegistrationStudentAndSignInModel
}
