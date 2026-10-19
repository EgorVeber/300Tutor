package org.v_points.data.account.mappers

import org.v_points.data.account.models.LogoutResponse
import org.v_points.data.common.network.LogoutErrorType
import org.v_points.domain.account.models.LogoutModel
import org.v_points.ui_common.EMPTY_STRING

fun LogoutResponse.toLogoutModel(): LogoutModel = LogoutModel(
    isSucceeded = isSucceeded ?: false,
    message = message ?: EMPTY_STRING,
    logoutErrorType = logoutErrorType ?: LogoutErrorType.NONE
)