package org.threehundredtutor.data.settings_app.model


import com.google.gson.annotations.SerializedName

class SettingsAppResponse(
    @SerializedName("applicationName")
    val applicationName: String?,
    @SerializedName("applicationUrl")
    val applicationUrl: String?,
    @SerializedName("publicImageUrlFormat")
    val publicImageUrlFormat: String?,
    @SerializedName("telegramBotSettings")
    val telegramBotSettingsResponse: TelegramBotSettingsResponse?,
    @SerializedName("imagesPack")
    val imagesPackResponse: ImagePackResponse?
)