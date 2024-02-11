package org.threehundredtutor.domain.main
data class EnterGroupModel(
    val succeeded: Boolean,
    val errorMessage: String,
    val groupId: String,
    val groupName: String,
)