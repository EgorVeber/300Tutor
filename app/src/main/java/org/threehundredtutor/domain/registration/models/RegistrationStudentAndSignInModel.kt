package org.threehundredtutor.domain.registration.models

data class RegistrationStudentAndSignInModel(
    val succeeded: Boolean,
    val message: String,
    val studentId: String
) {
    companion object {
        fun empty(): RegistrationStudentAndSignInModel = RegistrationStudentAndSignInModel(
            succeeded = false,
            message = "",
            studentId = ""
        )
    }
}
