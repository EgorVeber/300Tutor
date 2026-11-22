package com.v_points.app.vpoint.game_create.presentation

data class PlayerUiModel(
    val id: String,
    val name: String,
    val teamId: Int? = null,
    val checked: Boolean,
    val enabled: Boolean
)