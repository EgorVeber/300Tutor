package com.v_points.app.vpoint.game_day

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.components.game_day.GameDayCardColorful
import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.vpoint.game_day.presentation.GameDaysViewModel
import com.v_points.app.vpoint.game_day.presentation.MainScreenAction

@Composable
fun MainScreenRoute(
    viewModel: GameDaysViewModel,
) {
    val mainState = viewModel.getScreenGameDayState()

    Column(Modifier) {
        MainScreenContent(
            mainState = mainState,
            onAction = viewModel::onAction
        )
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    mainState: MainState,
    onAction: (MainScreenAction) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(mainState.items) { item ->
            GameDayCardColorful(
                gameDay = item,
                onClick = {
                    onAction.invoke(MainScreenAction.OnGameDayClick(item))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    MaterialTheme {
        val mockDay = GameDayUiModel(
            id = "1",
            formattedDate = "02 Ноября 2026",
            gymName = "Спортзал на Ленина",
            gamesCount = 5,
            isUserParticipated = true
        )


        MainScreenContent(
            onAction = {},
            mainState = MutableMainState().apply {
                items = buildList {
                    add(mockDay)
                    add(mockDay.copy(isUserParticipated = false))
                    add(mockDay)
                    add(mockDay.copy(isUserParticipated = false))
                    add(mockDay)
                    add(mockDay)
                    add(mockDay)
                    add(mockDay.copy(isUserParticipated = false))
                }
            },
        )
    }
}