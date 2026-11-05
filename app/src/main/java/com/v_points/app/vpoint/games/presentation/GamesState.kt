package com.v_points.app.vpoint.games.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel

interface GamesState {
    val gamesContentState: GamesContentState
}

class MutableGamesState : GamesState {
    override var gamesContentState: GamesContentState by mutableStateOf(GamesContentState.Empty)
}

sealed interface GamesContentState {
    data object Empty : GamesContentState
    data class Content(
        val gamesBlock: List<GameUiModel>,
        val participantsBlock: TeamParticipantsUiModel
    ) : GamesContentState
}
