package com.v_points.app.domain.account.models

data class CreateLoginLinkResultModel(
    val isSucceeded: Boolean,
    val errorMessage: String,
    val urlAuthentication: String,
)
