package org.threehundredtutor.domain.registration.models

data class RegistrationParamsModel(
    val email: String,
    val noEmail: Boolean,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val noPhoneNumber: Boolean,
    val password: String
)
