package org.threehundredtutor.data.restore

import com.google.gson.annotations.SerializedName
class RestoreResponse(
    @SerializedName("isSucceeded") val isSucceeded: Boolean?,
    @SerializedName("message") val message: String?,
)
