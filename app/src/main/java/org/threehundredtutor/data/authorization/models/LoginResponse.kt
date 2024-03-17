package org.threehundredtutor.data.authorization.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.common.network.ErrorType

class LoginResponse(
    @SerializedName("errorType") val errorType: ErrorType?, // передалть еррор тупе.
    @SerializedName("succeeded") val succeeded: Boolean?,
    @SerializedName("errorMessage") val errorMessage: String?,
)