package org.threehundredtutor.domain.settings_app

data class SettingsAppModel(
    val applicationName: String,
    val applicationUrl: String,
    val publicImageUrlFormat: String,
    val iconSettingsModel: IconSettingsModel,
    val telephoneInputOptionsModel: TelephoneInputOptionsModel,
    val webAppRoutesModel: WebAppRoutesModel,
    val telegramBotSettingsModel: TelegramBotSettingsModel,
    val imagesPack: ImagePackModel
) {
    companion object {
        val empty =
            SettingsAppModel(
                applicationName = "",
                applicationUrl = "",
                publicImageUrlFormat = "",
                telegramBotSettingsModel = TelegramBotSettingsModel.empty(),
                imagesPack = ImagePackModel.empty(),
                iconSettingsModel = IconSettingsModel.empty,
                webAppRoutesModel = WebAppRoutesModel.EMPTY,
                telephoneInputOptionsModel = TelephoneInputOptionsModel.EMPTY
            )
    }
}