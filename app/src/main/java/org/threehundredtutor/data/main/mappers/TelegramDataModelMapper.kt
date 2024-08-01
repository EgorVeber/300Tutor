package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.TelegramDataResponse
import org.threehundredtutor.domain.main.models.TelegramDataModel
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun TelegramDataResponse.toTelegramDataModel(): TelegramDataModel =
    TelegramDataModel(
        studentId = studentId.orEmpty(),
        telegramUserId = telegramUserId ?: DEFAULT_NOT_VALID_VALUE_INT,
        telegramUserName = telegramUserName.orEmpty()
    )