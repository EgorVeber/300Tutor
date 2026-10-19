package org.v_points.data.registration.mappers

import org.v_points.data.registration.models.StudentRegisterAndSignInResponse
import org.v_points.domain.registration.models.RegistrationStudentAndSignInModel

fun StudentRegisterAndSignInResponse.toRegistrationStudentAndSignInModel(): RegistrationStudentAndSignInModel =
    RegistrationStudentAndSignInModel(
        succeeded = succeded ?: false,
        message = message.orEmpty(),
        studentId = studentId.orEmpty(),
    )
