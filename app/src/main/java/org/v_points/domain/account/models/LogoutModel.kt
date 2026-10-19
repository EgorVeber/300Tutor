package org.v_points.domain.account.models

import org.v_points.data.common.network.LogoutErrorType

data class LogoutModel(
    val isSucceeded: Boolean,
    val message: String,
    val logoutErrorType: LogoutErrorType, // TODO домен
)