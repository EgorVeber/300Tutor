package org.threehundredtutor.data.authorization.mapper

import org.threehundredtutor.data.authorization.models.LoginDateRequest
import org.threehundredtutor.domain.authorization.LoginParamsModel

fun LoginParamsModel.toLoginDateRequest(): LoginDateRequest =
    LoginDateRequest(
        password = password,
        rememberMe = rememberMe,
        emailOrPhoneNumber = emailOrPhoneNumber
    )