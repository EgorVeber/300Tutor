package org.v_points.data.settings_app.model

import org.v_points.domain.settings_app.TelegramBotSettingsModel
import org.v_points.ui_common.util.orFalse

fun TelegramBotSettingsResponse.toTelegramBotSettingsModel(): TelegramBotSettingsModel =
    TelegramBotSettingsModel(
        botName = botName.orEmpty(),
        hasBot = hasBot.orFalse(),
        toBotWebButtonText = toBotWebButtonText.orEmpty(),
    )