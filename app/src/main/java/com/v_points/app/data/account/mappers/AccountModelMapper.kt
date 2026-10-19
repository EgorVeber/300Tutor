package com.v_points.app.data.account.mappers

import com.v_points.app.data.account.models.AccountResponse
import com.v_points.app.domain.account.models.AccountModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.ServerException

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