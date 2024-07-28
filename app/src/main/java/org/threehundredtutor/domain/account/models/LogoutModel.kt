package org.threehundredtutor.domain.account.models

import org.threehundredtutor.data.common.network.LogoutErrorType

data class LogoutModel(
    val isSucceeded: Boolean,
    val message: String,
    val logoutErrorType: LogoutErrorType, // TODO домен
)