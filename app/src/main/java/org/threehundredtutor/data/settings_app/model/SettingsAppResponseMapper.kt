package org.threehundredtutor.data.settings_app.model

import org.threehundredtutor.domain.settings_app.ImagePackModel
import org.threehundredtutor.domain.settings_app.SettingsAppModel
import org.threehundredtutor.domain.settings_app.TelegramBotSettingsModel

fun SettingsAppResponse.toSettingsAppModel(): SettingsAppModel =
    SettingsAppModel(
        applicationName = applicationName.orEmpty(),
        applicationUrl = applicationUrl.orEmpty(),
        publicImageUrlFormat = publicImageUrlFormat.orEmpty(),
        telegramBotSettingsModel = telegramBotSettingsResponse?.toTelegramBotSettingsModel()
            ?: TelegramBotSettingsModel.empty(),
        imagesPack = imagesPackResponse?.toImagePackModel() ?: ImagePackModel.empty()
    )
