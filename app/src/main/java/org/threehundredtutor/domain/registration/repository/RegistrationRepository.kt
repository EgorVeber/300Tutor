package org.threehundredtutor.domain.registration.repository

import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams

interface RegistrationRepository {

    suspend fun register(params: RegistrationParams): RegistrationModel
}
