package org.threehundredtutor.domain.authorization

import org.threehundredtutor.data.core.models.ErrorType

data class LoginModel(
    val errorType: ErrorType,
    val succeeded: Boolean,
    val errorMessage: String,
)
