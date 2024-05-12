package org.threehundredtutor.data.account.mappers

import org.threehundredtutor.data.account.models.AccountResponse
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.util.ServerException

fun AccountResponse.toAccountModel(): AccountModel = AccountModel(
    isAuthenticated = isAuthenticated ?: false,
    userId = userId ?: throw ServerException(),
    email = email ?: EMPTY_STRING,
    roles = roles,
    avatarFileId = avatarFileId ?: DEFAULT_NOT_VALID_VALUE_INT,
    name = name ?: EMPTY_STRING,
    surname = surname ?: EMPTY_STRING,
    phoneNumber = phoneNumber ?: EMPTY_STRING,
    noEmail = noEmail ?: true,
    noPhoneNumber = noPhoneNumber ?: true,
)