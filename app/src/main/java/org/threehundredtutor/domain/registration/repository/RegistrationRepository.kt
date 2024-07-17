package org.threehundredtutor.domain.registration.repository

import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationParamsModel
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel

interface RegistrationRepository {

    suspend fun registerStudent(params: RegistrationParamsModel): RegistrationStudentAndSignInModel
}
