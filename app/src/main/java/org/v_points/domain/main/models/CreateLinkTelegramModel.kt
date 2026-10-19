
package org.v_points.domain.main.models

data class CreateLinkTelegramModel(
    val isSucceeded: Boolean,
    val message: String,
    val command: String
)