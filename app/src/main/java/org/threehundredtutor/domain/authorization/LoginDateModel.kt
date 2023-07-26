package org.threehundredtutor.domain.authorization

data class LoginDateModel(
    val password: String,
    val rememberMe: Boolean,
    val emailOrPhoneNumber: String,
)