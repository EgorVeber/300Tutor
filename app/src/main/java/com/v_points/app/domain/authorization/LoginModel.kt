package com.v_points.app.domain.authorization

import com.v_points.app.data.common.network.ErrorType

data class LoginModel(
    val errorType: ErrorType,
    val succeeded: Boolean,
    val errorMessage: String,
) {
    companion object {
        fun empty(): LoginModel = LoginModel(
            errorType = ErrorType.NONE,
            succeeded = false,
            errorMessage = ""
        )
    }
}
