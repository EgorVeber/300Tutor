package com.v_points.app.data.settings_app.model

import com.v_points.app.domain.settings_app.IconSettingsModel
import com.v_points.app.domain.settings_app.ImagePackModel
import com.v_points.app.domain.settings_app.SettingsAppModel
import com.v_points.app.domain.settings_app.TelegramBotSettingsModel
import com.v_points.app.domain.settings_app.TelephoneInputOptionsModel
import com.v_points.app.domain.settings_app.WebAppRoutesModel

fun SettingsAppResponse.toSettingsAppModel(): SettingsAppModel =
    SettingsAppModel(
        applicationName = applicationName.orEmpty(),
        applicationUrl = applicationUrl.orEmpty(),
        publicImageUrlFormat = publicImageUrlFormat.orEmpty(),
        telegramBotSettingsModel = telegramBotSettingsResponse?.toTelegramBotSettingsModel()
            ?: TelegramBotSettingsModel.empty(),
        imagesPack = imagesPackResponse?.toImagePackModel() ?: ImagePackModel.empty(),
        iconSettingsModel = iconSettingsResponse?.toIconSettingsModel() ?: IconSettingsModel.empty,
        telephoneInputOptionsModel = telephoneInputOptionsResponse?.toTelephoneInputOptionsModel()
            ?: TelephoneInputOptionsModel.EMPTY,
        webAppRoutesModel = webAppRoutesResponse?.toWebAppRoutesModel() ?: WebAppRoutesModel.EMPTY
    )