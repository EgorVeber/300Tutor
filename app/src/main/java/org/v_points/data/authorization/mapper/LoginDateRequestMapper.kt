package org.v_points.data.authorization.mapper

import org.v_points.data.authorization.models.LoginDateRequest
import org.v_points.domain.authorization.LoginParamsModel

fun LoginParamsModel.toLoginDateRequest(): LoginDateRequest =
    LoginDateRequest(
        password = password,
        rememberMe = rememberMe,
        emailOrPhoneNumber = emailOrPhoneNumber
    )