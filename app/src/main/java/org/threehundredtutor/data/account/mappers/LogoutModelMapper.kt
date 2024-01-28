package org.threehundredtutor.data.account.mappers

import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.account.models.LogoutResponse
import org.threehundredtutor.domain.account.models.LogoutModel

fun LogoutResponse.toLogoutModel(): LogoutModel = LogoutModel(
    isSucceeded = isSucceeded ?: false,
    message = message ?: EMPTY_STRING
)