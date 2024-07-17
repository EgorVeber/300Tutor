package org.threehundredtutor.data.settings_app.model


import com.google.gson.annotations.SerializedName

class ImagePackResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("solutionFinished")
    val solutionFinished: String?,
    @SerializedName("success")
    val success: String?
)