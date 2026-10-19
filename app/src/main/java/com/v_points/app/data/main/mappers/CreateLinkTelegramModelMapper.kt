package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.CreateLinkTelegramResponse
import com.v_points.app.domain.main.models.CreateLinkTelegramModel
import com.v_points.app.ui_common.util.orFalse

fun CreateLinkTelegramResponse.toCreateLinkTelegramModel(): CreateLinkTelegramModel =
    CreateLinkTelegramModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        command = responseObject?.command.orEmpty()
    )