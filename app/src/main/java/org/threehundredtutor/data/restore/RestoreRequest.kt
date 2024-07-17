package org.threehundredtutor.data.restore

import com.google.gson.annotations.SerializedName

class RestoreRequest(
    @SerializedName("email")
    val email: String,
)
