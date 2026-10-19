package org.v_points.data.main.mappers

import org.v_points.data.main.response.CreateLinkTelegramResponse
import org.v_points.domain.main.models.CreateLinkTelegramModel
import org.v_points.ui_common.util.orFalse

fun CreateLinkTelegramResponse.toCreateLinkTelegramModel(): CreateLinkTelegramModel =
    CreateLinkTelegramModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        command = responseObject?.command.orEmpty()
    )