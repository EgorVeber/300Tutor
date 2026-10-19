package org.v_points.data.main.mappers

import org.v_points.data.main.response.TelegramDataResponse
import org.v_points.domain.main.models.TelegramDataModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun TelegramDataResponse.toTelegramDataModel(): TelegramDataModel =
    TelegramDataModel(
        studentId = studentId.orEmpty(),
        telegramUserId = telegramUserId ?: DEFAULT_NOT_VALID_VALUE_INT,
        telegramUserName = telegramUserName.orEmpty()
    )