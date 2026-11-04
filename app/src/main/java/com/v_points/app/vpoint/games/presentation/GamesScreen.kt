package com.v_points.app.vpoint.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.components.day.GameCardClassic
import com.v_points.app.ui_core.components.day.GameCardSplit
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.participant.ParticipantsCardAvatarsWithRating
import com.v_points.app.ui_core.components.participant.ParticipantsCardCompactV2
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel

@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
) {
    val state: MutableGamesState = viewModel.getScreenGameState()

    GamesScreenContent(state = state)
}

@Composable
fun GamesScreenContent(
    modifier: Modifier = Modifier,
    state: GamesState,
) {
    Column(Modifier.safeDrawingPadding()) {
        state.participantsBlock?.let { model ->
            ParticipantsBlock(state = model)
        }
        GamesBlock(modifier, state.gamesBlock)
    }
}

@Composable
private fun GamesBlock(
    modifier: Modifier,
    state: List<GameUiModel>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        state.forEach { game ->
            GameCardClassic(game = game) {}
        }
    }
}

@Composable
private fun ParticipantsBlock(
    modifier: Modifier = Modifier,
    state: TeamParticipantsUiModel
) {
    ParticipantsCardCompactV2(state)
}

@Preview(showBackground = true)
@Composable
fun GamesScreenContentPreview() {
    val mockParticipantsWithRatingWithRating = TeamParticipantsUiModel(
        teamOneName = "К1",
        teamTwoName = "К2",
        teamOnePlayers = listOf(
            PlayerUiModel("Иван", 67), PlayerUiModel("Вячеслав", 72),
            PlayerUiModel("Максим", 80), PlayerUiModel("Дима", 55),
            PlayerUiModel("Егор", 90), PlayerUiModel("Вадим", 61)
        ),
        teamTwoPlayers = listOf(
            PlayerUiModel("Евгений", 65), PlayerUiModel("Юля", 78),
            PlayerUiModel("Света", 45), PlayerUiModel("Саша", 88),
            PlayerUiModel("Дарья", 70), PlayerUiModel("Олеся", 59)
        )
    )

    MaterialTheme {
        GamesScreenContent(
            state = MutableGamesState().apply {
                gamesBlock = listOf(
                    GameUiModel("1", "Игра 1", "3 : 1", 5, 5),
                    GameUiModel("2", "Игра 2", "2 : 3", 4, 4)
                )
                participantsBlock = mockParticipantsWithRatingWithRating
            }
        )
    }
}
