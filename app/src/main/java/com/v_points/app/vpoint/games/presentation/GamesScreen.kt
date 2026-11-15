package com.v_points.app.vpoint.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.baseModifier
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.day.GamesListCardV4
import com.v_points.app.ui_core.components.nav_bar.NavBar
import com.v_points.app.ui_core.components.participant.ParticipantsCardCompactV2
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel
import com.v_points.app.ui_core.contentModifier
import kotlinx.coroutines.launch

@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
) {
    val state: MutableGamesState = viewModel.getScreenGameState()
    GamesScreenContent(state = state, onAction = viewModel::onNavigate)
}

@Composable
fun GamesScreenContent(
    state: GamesState,
    onAction: (GameScreenNavigateAction) -> Unit,
) {
    Column(baseModifier) {
        NavBar(
            title = state.title, onBackClick = {
                onAction.invoke(
                    GameScreenNavigateAction.OnBackClick
                )
            }
        )
        Box(contentModifier) {
            when (val data = state.gamesContentState) {
                is GamesContentState.Content -> {
                    val tabs = listOf("Игры", "Подачи", "Оценка")
                    val pagerState = rememberPagerState(pageCount = { tabs.size })
                    val coroutineScope = rememberCoroutineScope()
                    Column {
                        TabRow(selectedTabIndex = pagerState.currentPage) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                    text = { Text(text = title) }
                                )
                            }
                        }
                        HorizontalPager(state = pagerState) { page ->
                            when (page) {
                                0 -> {
                                    Column(
                                        Modifier
                                            .verticalScroll(rememberScrollState())
                                            .padding(PaddingValues(top = 8.dp, bottom = 24.dp)),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        ParticipantsCardCompactV2(data.participantsBlock)
                                        GamesListCardV4(games = data.gamesBlock)
                                    }
                                }

                                1 -> {
                                    Text(text = "Содержимое: ${tabs[page]}")
                                }

                                2 -> {
                                    Text(text = "Содержимое: ${tabs[page]}")
                                }

                                else -> {
                                    Text(text = "Содержимое: ${tabs[page]}")
                                }
                            }
                        }
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
                title = "17 Октября 2026"
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
            },
            onAction = {},
        )
    }
}
