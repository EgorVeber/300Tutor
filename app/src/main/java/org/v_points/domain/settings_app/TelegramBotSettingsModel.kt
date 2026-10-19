package org.v_points.domain.settings_app

class TelegramBotSettingsModel(
    val botName: String,
    val hasBot: Boolean,
    val toBotWebButtonText: String
) {
    companion object {
        fun empty() = TelegramBotSettingsModel("", false, "")
    }
}