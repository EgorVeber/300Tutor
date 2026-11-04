package com.v_points.app.ui_core.components.day
data class GameUiModel(
    val id: String,
    val name: String,
    val scoreText: String,
    val scoreTeamOne: Int,
    val scoreTeamTwo: Int,
    val teamOneName: String = "К1",
    val teamTwoName: String = "К2"
)