package org.threehundredtutor.data.account.mappers

import org.threehundredtutor.data.account.models.LogoutResponse
import org.threehundredtutor.data.common.network.LogoutErrorType
import org.threehundredtutor.domain.account.models.LogoutModel
import org.threehundredtutor.ui_common.EMPTY_STRING

fun LogoutResponse.toLogoutModel(): LogoutModel = LogoutModel(
    isSucceeded = isSucceeded ?: false,
    message = message ?: EMPTY_STRING,
    logoutErrorType = logoutErrorType ?: LogoutErrorType.NONE
)