
package com.v_points.app.domain.main.models

data class CreateLinkTelegramModel(
    val isSucceeded: Boolean,
    val message: String,
    val command: String
)