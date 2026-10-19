package org.v_points.data.authorization.models

import com.google.gson.annotations.SerializedName
import org.v_points.data.common.network.ErrorType

class LoginResponse(
    @SerializedName("errorType") val errorType: ErrorType?, // передалть еррор тупе.
    @SerializedName("succeeded") val succeeded: Boolean?,
    @SerializedName("errorMessage") val errorMessage: String?,
)