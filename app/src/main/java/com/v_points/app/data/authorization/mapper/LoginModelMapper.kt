package com.v_points.app.data.authorization.mapper

import com.v_points.app.data.authorization.models.LoginResponse
import com.v_points.app.data.common.network.ErrorType
import com.v_points.app.domain.authorization.LoginModel
import com.v_points.app.ui_common.EMPTY_STRING


fun LoginResponse.toLoginModel(): LoginModel = LoginModel(
    errorType = errorType ?: ErrorType.NONE,
    succeeded = succeeded ?: false,
    errorMessage = errorMessage ?: EMPTY_STRING
)