package com.v_points.app.domain.authorization

data class LoginParamsModel(
    val password: String,
    val rememberMe: Boolean,
    val emailOrPhoneNumber: String,
)