package org.threehundredtutor.data.account.mappers

import org.threehundredtutor.data.account.models.CreateLoginLinkResultResponse
import org.threehundredtutor.domain.account.models.CreateLoginLinkResultModel
import org.threehundredtutor.ui_common.util.orFalse

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