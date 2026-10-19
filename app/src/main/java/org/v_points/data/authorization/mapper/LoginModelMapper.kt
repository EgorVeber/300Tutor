package org.v_points.data.authorization.mapper

import org.v_points.data.authorization.models.LoginResponse
import org.v_points.data.common.network.ErrorType
import org.v_points.domain.authorization.LoginModel
import org.v_points.ui_common.EMPTY_STRING


fun LoginResponse.toLoginModel(): LoginModel = LoginModel(
    errorType = errorType ?: ErrorType.NONE,
    succeeded = succeeded ?: false,
    errorMessage = errorMessage ?: EMPTY_STRING
)