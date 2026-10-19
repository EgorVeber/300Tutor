package org.v_points.domain.registration.repository

import org.v_points.domain.registration.models.RegistrationParamsModel
import org.v_points.domain.registration.models.RegistrationStudentAndSignInModel

interface RegistrationRepository {

    suspend fun registerStudent(params: RegistrationParamsModel): RegistrationStudentAndSignInModel
}
