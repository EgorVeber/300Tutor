package org.v_points.domain.account.models

data class CreateLoginLinkResultModel(
    val isSucceeded: Boolean,
    val errorMessage: String,
    val urlAuthentication: String,
)
