package org.threehundredtutor.data.account

import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.domain.account.LogoutModel

fun LogoutResponse.toLogoutModel(): LogoutModel = LogoutModel(
    isSucceeded = isSucceeded ?: false,
    message = message ?: EMPTY_STRING
)