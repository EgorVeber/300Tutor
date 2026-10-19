package com.v_points.app.data.registration.mappers

import com.v_points.app.data.registration.models.StudentRegisterAndSignInResponse
import com.v_points.app.domain.registration.models.RegistrationStudentAndSignInModel

fun StudentRegisterAndSignInResponse.toRegistrationStudentAndSignInModel(): RegistrationStudentAndSignInModel =
    RegistrationStudentAndSignInModel(
        succeeded = succeded ?: false,
        message = message.orEmpty(),
        studentId = studentId.orEmpty(),
    )
