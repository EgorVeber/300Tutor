package org.threehundredtutor.data.settings_app.model

import org.threehundredtutor.domain.settings_app.TelegramBotSettingsModel
import org.threehundredtutor.ui_common.util.orFalse

fun TelegramBotSettingsResponse.toTelegramBotSettingsModel(): TelegramBotSettingsModel =
    TelegramBotSettingsModel(
        botName = botName.orEmpty(),
        hasBot = hasBot.orFalse(),
        toBotWebButtonText = toBotWebButtonText.orEmpty(),
    )