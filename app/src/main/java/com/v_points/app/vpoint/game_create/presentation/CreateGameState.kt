package com.v_points.app.vpoint.game_create.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

interface CreateGameState {
    val gameName: String
    val gameNameCache: String
    val scoreTitle: String
    val playersOneTeamTitle: String
    val playersTwoTeamTitle: String
    val playersBottomTitle: String
    val isCreateEnabled: Boolean
    val isScoreEnabled: Boolean
    val showPlayersBottom: Boolean
    val showNameBottom: Boolean
    val showScoreBottom: Boolean
    val players: List<PlayerUiModel>
    val enabledSaveBtnPLayer: Boolean
    val scoreOneTeam: Int
    val scoreTwoTeam: Int
}

class MutableCreateGameState : CreateGameState {
    override var gameNameCache: String by mutableStateOf("")
    override var gameName: String by mutableStateOf("")
    override var scoreTitle: String by mutableStateOf("")
    override var playersBottomTitle: String by mutableStateOf("")
    override var playersOneTeamTitle: String by mutableStateOf("")
    override var playersTwoTeamTitle: String by mutableStateOf("")
    override var players: List<PlayerUiModel> by mutableStateOf(listOf())
    override var isCreateEnabled: Boolean by mutableStateOf(false)
    override var isScoreEnabled: Boolean by mutableStateOf(false)
    override var showPlayersBottom: Boolean by mutableStateOf(false)
    override var showScoreBottom: Boolean by mutableStateOf(false)
    override var showNameBottom: Boolean by mutableStateOf(false)
    override var enabledSaveBtnPLayer: Boolean by mutableStateOf(true)
    override var scoreOneTeam: Int by mutableIntStateOf(25)
    override var scoreTwoTeam: Int by mutableIntStateOf(25)
}
