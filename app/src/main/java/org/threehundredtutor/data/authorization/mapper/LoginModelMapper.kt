package org.threehundredtutor.data.authorization.mapper

import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.authorization.models.LoginResponse
import org.threehundredtutor.data.core.models.ErrorType
import org.threehundredtutor.domain.authorization.LoginModel


fun LoginResponse.toLoginModel(): LoginModel = LoginModel(
    errorType = errorType ?: ErrorType.NONE,
    succeeded = succeeded ?: false,
    errorMessage = errorMessage ?: EMPTY_STRING
)