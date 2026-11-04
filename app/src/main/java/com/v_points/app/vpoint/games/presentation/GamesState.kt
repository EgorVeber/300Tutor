package com.v_points.app.vpoint.games.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel

interface GamesState {
    val gamesBlock: List<GameUiModel>
    val participantsBlock: TeamParticipantsUiModel?
}

class MutableGamesState : GamesState {
    override var gamesBlock: List<GameUiModel> by mutableStateOf(listOf())
    override var participantsBlock: TeamParticipantsUiModel? by mutableStateOf(null)
}
