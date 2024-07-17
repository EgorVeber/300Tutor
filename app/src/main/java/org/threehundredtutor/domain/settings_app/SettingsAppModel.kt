package org.threehundredtutor.domain.settings_app


data class SettingsAppModel(
    val applicationName: String,
    val applicationUrl: String,
    val publicImageUrlFormat: String,
    val telegramBotSettingsModel: TelegramBotSettingsModel,
    val imagesPack: ImagePackModel
) {
    companion object {
        val empty =
            SettingsAppModel(
                "",
                "",
                "",
                TelegramBotSettingsModel.empty(),
                ImagePackModel.empty()
            )
    }
}