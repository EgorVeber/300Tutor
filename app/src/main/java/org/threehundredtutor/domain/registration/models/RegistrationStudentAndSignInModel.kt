package org.threehundredtutor.domain.registration.models

data class RegistrationStudentAndSignInModel(
    val succeded: Boolean,
    val message: String,
    val studentId: String
) {
    companion object {
        fun empty(): RegistrationStudentAndSignInModel = RegistrationStudentAndSignInModel(
            succeded = false,
            message = "",
            studentId = ""
        )
    }
}
