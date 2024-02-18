package org.threehundredtutor.data.registration.mappers

import org.threehundredtutor.data.registration.models.StudentRegisterAndSignInResponse
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel

fun StudentRegisterAndSignInResponse.toRegistrationStudentAndSignInModel(): RegistrationStudentAndSignInModel =
    RegistrationStudentAndSignInModel(
        succeeded = succeded ?: false,
        message = message.orEmpty(),
        studentId = studentId.orEmpty(),
    )
