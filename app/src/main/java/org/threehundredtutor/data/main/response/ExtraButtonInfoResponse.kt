package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class ExtraButtonInfoResponse(
    @SerializedName("color")
    val color: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("text")
    val text: String?
)