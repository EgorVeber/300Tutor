package org.threehundredtutor.data.authorization.mapper

import org.threehundredtutor.data.authorization.models.LoginResponse
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.authorization.LoginModel
import org.threehundredtutor.ui_common.EMPTY_STRING


fun LoginResponse.toLoginModel(): LoginModel = LoginModel(
    errorType = errorType ?: ErrorType.NONE,
    succeeded = succeeded ?: false,
    errorMessage = errorMessage ?: EMPTY_STRING
)