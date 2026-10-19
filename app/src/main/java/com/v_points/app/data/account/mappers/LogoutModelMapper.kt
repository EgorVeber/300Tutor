package com.v_points.app.data.account.mappers

import com.v_points.app.data.account.models.LogoutResponse
import com.v_points.app.data.common.network.LogoutErrorType
import com.v_points.app.domain.account.models.LogoutModel
import com.v_points.app.ui_common.EMPTY_STRING

fun LogoutResponse.toLogoutModel(): LogoutModel = LogoutModel(
    isSucceeded = isSucceeded ?: false,
    message = message ?: EMPTY_STRING,
    logoutErrorType = logoutErrorType ?: LogoutErrorType.NONE
)