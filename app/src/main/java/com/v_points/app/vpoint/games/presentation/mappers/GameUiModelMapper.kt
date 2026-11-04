package com.v_points.app.vpoint.games.presentation.mappers

import androidx.room.Index
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel
import com.v_points.app.vpoint.fiersote.domain.GameModel

fun GameModel.toGameUiModel(index: Int): GameUiModel = GameUiModel(
    id = id,
    name = name.ifEmpty { "Игра #${id.take(4)}" },
    scoreText = "$scoreTeamOne : $scoreTeamTwo",
    scoreTeamOne = scoreTeamOne,
    scoreTeamTwo = scoreTeamTwo
)