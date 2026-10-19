package com.v_points.app.data.authorization.mapper

import com.v_points.app.data.authorization.models.LoginDateRequest
import com.v_points.app.domain.authorization.LoginParamsModel

fun LoginParamsModel.toLoginDateRequest(): LoginDateRequest =
    LoginDateRequest(
        password = password,
        rememberMe = rememberMe,
        emailOrPhoneNumber = emailOrPhoneNumber
    )