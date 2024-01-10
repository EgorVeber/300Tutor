package org.threehundredtutor.data.account.mappers

import org.threehundredtutor.common.PATH_LOGIN
import org.threehundredtutor.common.SITE_URL
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.account.models.CreateLoginLinkResultResponse
import org.threehundredtutor.domain.account.models.CreateLoginLinkResultModel

fun CreateLoginLinkResultResponse.toCreateLoginLinkResultModel(): CreateLoginLinkResultModel =
    CreateLoginLinkResultModel(
        isSucceeded = isSucceeded.orFalse(),
        errorMessage = errorMessage.orEmpty(),
        urlAuthentication = StringBuilder()
            .append(SITE_URL)
            .append("/")
            .append(PATH_LOGIN)
            .append("/")
            .append(linkId)
            .append("/")
            .append(password)
            .toString(),
    )