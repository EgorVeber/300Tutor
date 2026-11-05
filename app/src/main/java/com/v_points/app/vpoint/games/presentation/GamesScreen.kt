package com.v_points.app.vpoint.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.v_points.app.ui_core.baseModifier
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.day.GamesListCardV4
import com.v_points.app.ui_core.components.nav_bar.NavBar
import com.v_points.app.ui_core.components.participant.ParticipantsCardCompactV2
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel
import com.v_points.app.ui_core.contentModifier

@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
) {
    val state: MutableGamesState = viewModel.getScreenGameState()
    GamesScreenContent(state = state)
}

@Composable
fun GamesScreenContent(
    state: GamesState,
) {
    Column(baseModifier) {
        NavBar(title = "Игровой день", subtitle = "17.06.2026", onBackClick = {})
        Box(contentModifier) {
            when (val data = state.gamesContentState) {
                is GamesContentState.Content -> {
                    Column(
                        Modifier.verticalScroll(rememberScrollState()).padding(PaddingValues(top = 8.dp, bottom = 24.dp)),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ParticipantsCardCompactV2(data.participantsBlock)
                        GamesListCardV4(games = data.gamesBlock)
                    }
                }

                GamesContentState.Empty -> {}
            }
        }
    }
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
                gamesContentState = GamesContentState.Content(
                    gamesBlock = listOf(
                        GameUiModel("1", "Игра 1", "3 : 1", 5, 5),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                        GameUiModel("2", "Игра 2", "2 : 3", 4, 4),
                    ),
                    participantsBlock = mockParticipantsWithRatingWithRating
                )
            }
        )
    }
}
