package org.threehundredtutor.data.settings_app.model


import com.google.gson.annotations.SerializedName

class SettingsAppResponse(
    @SerializedName("applicationName")
    val applicationName: String?,
    @SerializedName("applicationUrl")
    val applicationUrl: String?,
    @SerializedName("icon")
    val iconSettingsResponse: IconSettingsResponse?,
    @SerializedName("publicImageUrlFormat")
    val publicImageUrlFormat: String?,
    @SerializedName("telegramBotSettings")
    val telegramBotSettingsResponse: TelegramBotSettingsResponse?,
    @SerializedName("imagesPack")
    val imagesPackResponse: ImagePackResponse?,
    @SerializedName("telephoneInputOptions")
    val telephoneInputOptionsResponse: TelephoneInputOptionsResponse?,
    @SerializedName("webAppRoutes")
    val webAppRoutesResponse: WebAppRoutesResponse?
)