package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.TelegramDataResponse
import com.v_points.app.domain.main.models.TelegramDataModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun TelegramDataResponse.toTelegramDataModel(): TelegramDataModel =
    TelegramDataModel(
        studentId = studentId.orEmpty(),
        telegramUserId = telegramUserId ?: DEFAULT_NOT_VALID_VALUE_INT,
        telegramUserName = telegramUserName.orEmpty()
    )