package org.v_points.domain.authorization

data class LoginParamsModel(
    val password: String,
    val rememberMe: Boolean,
    val emailOrPhoneNumber: String,
)