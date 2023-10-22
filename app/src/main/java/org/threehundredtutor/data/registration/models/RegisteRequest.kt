package org.threehundredtutor.data.registration.models

class RegisteRequest(
    val email: String,
    val noEmail: Boolean,
    val name: String,
    val surname: String,
    val patronymic: String,
    val phoneNumber: String,
    val noPhoneNumber: Boolean,
    val password: String,
)
