package org.v_points.data.account.mappers

import org.v_points.data.account.models.AccountResponse
import org.v_points.domain.account.models.AccountModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.ServerException

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