package com.v_points.app.data.account.mappers

import com.v_points.app.data.account.models.CreateLoginLinkResultResponse
import com.v_points.app.domain.account.models.CreateLoginLinkResultModel
import com.v_points.app.ui_common.util.orFalse

private const val PATH_LOGIN = "login-by-link"
fun CreateLoginLinkResultResponse.toCreateLoginLinkResultModel(
    siteUrl: String,
): CreateLoginLinkResultModel =
    CreateLoginLinkResultModel(
        isSucceeded = isSucceeded.orFalse(),
        errorMessage = errorMessage.orEmpty(),
        urlAuthentication = StringBuilder()
            .append(siteUrl)
            .append("/")
            .append(PATH_LOGIN)
            .append("/")
            .append(linkId)
            .append("/")
            .append(password)
            .toString(),
    )