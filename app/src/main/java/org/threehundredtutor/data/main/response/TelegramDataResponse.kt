package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName
class TelegramDataResponse(
    @SerializedName("studentId")
    val studentId: String?,
    @SerializedName("telegramUserId")
    val telegramUserId: Int?,
    @SerializedName("telegramUserName")
    val telegramUserName: String?
)