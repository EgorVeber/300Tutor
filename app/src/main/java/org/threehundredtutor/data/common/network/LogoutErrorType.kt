package org.threehundredtutor.data.common.network

import com.google.gson.annotations.SerializedName

enum class LogoutErrorType {
    @SerializedName("NotAuthenticated")
    NOT_AUTHENTICATED,
    @SerializedName("SignoutError")
    SIGN_OUT_ERROR,
    NONE
}