package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class ExtraButtonResponse(
    @SerializedName("buttons")
    val extraButtonInfoResponses: List<ExtraButtonInfoResponse>?
)