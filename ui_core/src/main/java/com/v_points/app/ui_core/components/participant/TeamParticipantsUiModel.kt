package com.v_points.app.ui_core.components.participant

data class TeamParticipantsUiModel(
    val teamOneName: String = "К1",
    val teamTwoName: String = "К2",
    val teamOnePlayers: List<PlayerUiModel>,
    val teamTwoPlayers: List<PlayerUiModel>
)

data class PlayerUiModel(
    val name: String,
    val rating: Int,
    val isCurrentUser: Boolean = false
)