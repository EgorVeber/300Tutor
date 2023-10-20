package org.threehundredtutor.domain.registration.repository

import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationParams
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel

interface RegistrationRepository {

    suspend fun registerAccount(params: RegistrationParams): RegistrationAccountAndSignInModel
    suspend fun registerStudent(params: RegistrationParams): RegistrationStudentAndSignInModel
}
