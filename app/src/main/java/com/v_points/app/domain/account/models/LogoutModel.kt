package com.v_points.app.domain.account.models

import com.v_points.app.data.common.network.LogoutErrorType

data class LogoutModel(
    val isSucceeded: Boolean,
    val message: String,
    val logoutErrorType: LogoutErrorType, // TODO домен
)