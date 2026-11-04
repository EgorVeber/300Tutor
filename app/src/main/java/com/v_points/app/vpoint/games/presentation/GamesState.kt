package com.v_points.app.vpoint.games.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class GameUiModel(
    val id: String,
    val name: String,
    val scoreText: String,
    val teamOneCount: Int,
    val teamTwoCount: Int
)

interface GamesState {
    val games: List<GameUiModel>
}

class MutableGamesState : GamesState {
    override var games: List<GameUiModel> by mutableStateOf(listOf())
}
