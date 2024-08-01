
package org.threehundredtutor.domain.main.models

data class CreateLinkTelegramModel(
    val isSucceeded: Boolean,
    val message: String,
    val command: String
)