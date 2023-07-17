package org.threehundredtutor.domain.registration.repository

import org.threehundredtutor.domain.registration.models.RegisteredUserModel

interface RegistrationRepository {

    fun register(): RegisteredUserModel
}
