package com.v_points.app.vpoint.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.components.menu_item.MainMenuItem
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel
import com.v_points.app.ui_core.theme.TutorTheme

@Composable
fun MainScreenRoute(
    viewModel: MainViewModel,
) {
    val mainState = viewModel.getMainState()

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
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mainState.items) { item ->
                MainMenuItem(
                    mainMenuUiModel = item,
                    onClick = {
                        onAction.invoke(MainScreenAction.OnMenuItemCLick(item))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuTilesPreview() {
    TutorTheme {
        MainScreenContent(
            onAction = {},
            mainState = MutableMainState().apply {
                items = buildList {
                    add(
                        MainMenuUiModel.Users(
                            title = "Участники",
                            subtitle = null,
                            iconRes = null
                        )
                    )
                    add(
                        MainMenuUiModel.Games(
                            title = "Игры",
                            subtitle = null,
                            iconRes = null
                        )
                    )
                    add(
                        MainMenuUiModel.Profile(
                            title = "Профиль",
                            subtitle = null,
                            iconRes = null
                        )
                    )
                }
            },
        )
    }
}