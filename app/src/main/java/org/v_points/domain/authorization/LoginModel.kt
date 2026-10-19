package org.v_points.domain.authorization

import org.v_points.data.common.network.ErrorType

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
