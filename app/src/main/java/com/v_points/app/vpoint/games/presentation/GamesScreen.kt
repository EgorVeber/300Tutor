package com.v_points.app.vpoint.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.baseModifier
import com.v_points.app.ui_core.components.day.GameUiModel
import com.v_points.app.ui_core.components.day.GamesListCardV4
import com.v_points.app.ui_core.components.participant.ParticipantsCardCompactV2
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel
import kotlinx.coroutines.launch

@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
) {
    val state: MutableGamesState = viewModel.getScreenGameState()
    GamesScreenContent(state = state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreenContent(
    state: GamesState,
    onAction: (GameScreenAction) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        TopAppBar(
            title = {
                Text(text = state.title)
            },

            navigationIcon = {
                IconButton(onClick = {
                    onAction.invoke(
                        GameScreenAction.OnBackClick
                    )
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }, actions = {
                IconButton(onClick = {
                    onAction.invoke(
                        GameScreenAction.OnCreateGameClick
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
        Box {
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
                                            .padding(PaddingValues(top = 8.dp, bottom = 24.dp))
                                            .padding(horizontal = 16.dp),
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
            PlayerUiModel(id="", "Иван", 67), PlayerUiModel(id="", "Вячеслав", 72),
            PlayerUiModel(id="", "Максим", 80), PlayerUiModel(id="", "Дима", 55),
            PlayerUiModel(id="", "Егор", 90), PlayerUiModel(id="", "Вадим", 61)
        ),
        teamTwoPlayers = listOf(
            PlayerUiModel(id="", "Евгений", 65), PlayerUiModel(id="", "Юля", 78),
            PlayerUiModel(id="", "Света", 45), PlayerUiModel(id="", "Саша", 88),
            PlayerUiModel(id="", "Дарья", 70), PlayerUiModel(id="", "Олеся", 59)
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
