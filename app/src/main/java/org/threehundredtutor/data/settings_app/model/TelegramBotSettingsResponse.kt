package org.threehundredtutor.data.settings_app.model

import com.google.gson.annotations.SerializedName

class TelegramBotSettingsResponse(
    @SerializedName("botName")
    val botName: String?,
    @SerializedName("hasBot")
    val hasBot: Boolean?,
    @SerializedName("toBotWebButtonText")
    val toBotWebButtonText: String?
)