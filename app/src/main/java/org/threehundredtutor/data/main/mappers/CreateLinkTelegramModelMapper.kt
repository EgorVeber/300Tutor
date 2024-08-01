package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.CreateLinkTelegramResponse
import org.threehundredtutor.domain.main.models.CreateLinkTelegramModel
import org.threehundredtutor.ui_common.util.orFalse

fun CreateLinkTelegramResponse.toCreateLinkTelegramModel(): CreateLinkTelegramModel =
    CreateLinkTelegramModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        command = responseObject?.command.orEmpty()
    )