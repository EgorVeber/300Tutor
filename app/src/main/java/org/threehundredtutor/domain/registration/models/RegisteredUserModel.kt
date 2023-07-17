package org.threehundredtutor.domain.registration.models

data class RegisteredUserModel(
    val id: String,
    val phoneNumber: String,
    val email: String,
) {
    companion object {
        fun empty(): RegisteredUserModel =
            RegisteredUserModel(
                id = "",
                phoneNumber = "",
                email = ""
            )
    }
}
