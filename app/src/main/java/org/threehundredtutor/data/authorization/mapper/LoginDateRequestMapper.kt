package org.threehundredtutor.data.authorization.mapper

import org.threehundredtutor.data.authorization.models.LoginDateRequest
import org.threehundredtutor.domain.authorization.LoginDateModel

fun LoginDateModel.toLoginDateRequest(): LoginDateRequest =
    LoginDateRequest(
        password = password,
        rememberMe = rememberMe,
        emailOrPhoneNumber = emailOrPhoneNumber
    )