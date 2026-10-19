package com.v_points.app.data.settings_app.model

import com.v_points.app.domain.settings_app.TelegramBotSettingsModel
import com.v_points.app.ui_common.util.orFalse

fun TelegramBotSettingsResponse.toTelegramBotSettingsModel(): TelegramBotSettingsModel =
    TelegramBotSettingsModel(
        botName = botName.orEmpty(),
        hasBot = hasBot.orFalse(),
        toBotWebButtonText = toBotWebButtonText.orEmpty(),
    )