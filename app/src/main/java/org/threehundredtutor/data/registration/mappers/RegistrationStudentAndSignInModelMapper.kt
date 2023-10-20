package org.threehundredtutor.data.registration.mappers

import org.threehundredtutor.data.registration.models.StudentRegisterAndSignIn
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel

fun StudentRegisterAndSignIn.toRegistrationStudentAndSignInModel(): RegistrationStudentAndSignInModel =
    RegistrationStudentAndSignInModel(
        succeded = succeded ?: false,
        message = message.orEmpty(),
        studentId = studentId.orEmpty(),
    )
