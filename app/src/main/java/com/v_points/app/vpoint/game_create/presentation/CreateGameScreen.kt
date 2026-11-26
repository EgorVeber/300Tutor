package com.v_points.app.vpoint.game_create.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v_points.app.ui_core.components.FullScreenLoader
import com.v_points.app.ui_core.components.menu_variants.MenuListCard

@Composable
fun CreateGameScreen(
    viewModel: CreateGameViewModel,
    onBackClick: () -> Unit
) {
    val state = viewModel.getState()
    CreateGameScreenContent(
        state = state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameScreenContent(
    modifier: Modifier = Modifier,
    state: CreateGameState,
    onAction: (CreateGameAction) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        TopAppBar(
            title = {
                Text(text = "Создать игру")
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
        ) {
            MenuListCard(
                title = "Название игры",
                showSuccessIcon = state.gameNameCache.isNotEmpty(),
                subtitle = state.gameNameCache.ifEmpty { "Нажмите, чтобы добавить название" },
                imageVector = Icons.Default.SportsVolleyball,
                onClick = { onAction(CreateGameAction.OnNameClick) },
                shoNextIcon = false
            )
            MenuListCard(
                title = "Игроки K1",
                showSuccessIcon = state.playersOneTeamTitle.isNotEmpty(),
                subtitle = state.playersOneTeamTitle.ifEmpty { "Нажмите, чтобы добавить игроков" },
                imageVector = Icons.Default.SupervisedUserCircle,
                onClick = { onAction(CreateGameAction.OnPlayersOneTeamClick) },
                shoNextIcon = false
            )
            MenuListCard(
                title = "Игроки K2",
                showSuccessIcon = state.playersTwoTeamTitle.isNotEmpty(),
                subtitle = state.playersTwoTeamTitle.ifEmpty { "Нажмите, чтобы добавить игроков" },
                imageVector = Icons.Default.SupervisedUserCircle,
                onClick = { onAction(CreateGameAction.OnPlayersTwoTeamClick) },
                shoNextIcon = false
            )
            MenuListCard(
                title = "Счет",
                showSuccessIcon = state.scoreTitle.isNotEmpty(),
                subtitle = state.scoreTitle.ifEmpty { "Нажмите, чтобы добавить счет" },
                imageVector = Icons.Default.Scoreboard,
                onClick = { onAction(CreateGameAction.OnScoreClick) },
                shoNextIcon = false
            )
        }

        Button(
            onClick = { onAction(CreateGameAction.OnCreateClick) },
            enabled = state.isCreateEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Создать", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }

    PLayersBottom(state, onAction)
    NameInputBottomSheet(state, onAction)
    ScoreBottomSheet(state, onAction)
    FullScreenLoader(isLoading = state.loading)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PLayersBottom(
    state: CreateGameState,
    onAction: (CreateGameAction) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(true)
    if (state.showPlayersBottom) {
        ModalBottomSheet(
            modifier = Modifier.statusBarsPadding(),
            onDismissRequest = { onAction(CreateGameAction.OnDismissPlayers) },
            sheetState = sheetState,
        ) {
            PlayerSelectionSheet(
                playersBottomTitle = state.playersBottomTitle,
                players = state.players,
                enabledSaveBtn = state.enabledSaveBtnPLayer,
                onAction = onAction
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameInputBottomSheet(
    state: CreateGameState,
    onAction: (CreateGameAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (state.showNameBottom) {
        ModalBottomSheet(
            modifier = Modifier.statusBarsPadding(),
            onDismissRequest = { onAction(CreateGameAction.OnDismissNames) },
            sheetState = sheetState,
        ) {
            NameDialogContent(state.gameName, focusManager, onAction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBottomSheet(
    state: CreateGameState,
    onAction: (CreateGameAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (state.showScoreBottom) {
        ModalBottomSheet(
            modifier = Modifier.statusBarsPadding(),
            onDismissRequest = { onAction(CreateGameAction.OnDismissScore) },
            sheetState = sheetState,
        ) {
            ScoreBottomContent(state.scoreOneTeam, state.scoreTwoTeam, onAction)
        }
    }
}

@Composable
private fun ScoreBottomContent(
    scoreOneTeam: Int,
    scoreTwoTeam: Int,
    onAction: (CreateGameAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Выберите счет",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberWheel(
                label = "К1",
                initialValue = scoreOneTeam,
                onValueChange = { onAction.invoke(CreateGameAction.OnScoreOneChange(it)) }
            )
            NumberWheel(
                label = "К2",
                initialValue = scoreTwoTeam,
                onValueChange = { onAction.invoke(CreateGameAction.OnScoreTwoChange(it)) }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                onAction.invoke(CreateGameAction.OnScoreSaveClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Сохранить", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun NameDialogContent(
    name: String,
    focusManager: FocusManager,
    onAction: (CreateGameAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Введите название игры",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                onAction.invoke(CreateGameAction.OnNameTextChanges(it))
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onAction.invoke(CreateGameAction.OnSaveNameClick)
                }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onAction.invoke(CreateGameAction.OnSaveNameClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Сохранить", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PlayerSelectionSheet(
    modifier: Modifier = Modifier,
    players: List<PlayerUiModel>,
    playersBottomTitle: String,
    enabledSaveBtn: Boolean,
    onAction: (CreateGameAction) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = playersBottomTitle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .weight(1f, fill = true),
        ) {
            items(players, key = { it.id }) { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = player.checked,
                        enabled = player.enabled,
                        onCheckedChange = { checked ->
                            onAction.invoke(CreateGameAction.OnCheckedChange(player.id, checked))
                        }
                    )
                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Button(
            enabled = enabledSaveBtn,
            onClick = {
                onAction.invoke(CreateGameAction.OnSavePlayersBottomClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Сохранить", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGameScreenPreview() {
    val sample = List(12) {
        PlayerUiModel(
            it.toString(),
            "Игрок ${it + 1}",
            checked = true,
            enabled = true
        )
    }

    MaterialTheme {
        CreateGameScreenContent(
            state = MutableCreateGameState().apply {
                isCreateEnabled = true
                players = sample
            },
            onAction = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGameScreenPreviewV2() {
    val sample = List(12) {
        PlayerUiModel(
            it.toString(),
            "Игрок ${it + 1}",
            checked = true,
            enabled = true
        )
    }

    MaterialTheme {
        CreateGameScreenContent(
            state = MutableCreateGameState().apply {
                gameName = "Финал турнира"
                scoreTitle = "К1 23:25 K2"
                playersOneTeamTitle = "Заполнено"
                isCreateEnabled = true
                players = sample
            },
            onAction = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PLayersBottomPreview() {
    val sample = List(12) {
        PlayerUiModel(
            it.toString(),
            "Игрок ${it + 1}",
            checked = true,
            enabled = true
        )
    }

    MaterialTheme {
        PlayerSelectionSheet(
            enabledSaveBtn = false,
            players = sample,
            playersBottomTitle = "Выберите игроков К542",
        ) {}
    }
}


@Preview(showBackground = true)
@Composable
fun NamesBottomPreview() {
    val focusManager = LocalFocusManager.current
    MaterialTheme {
        NameDialogContent(
            name = "",
            focusManager = focusManager,
            onAction = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreBottomPreview() {
    MaterialTheme {
        ScoreBottomContent(2, 3) {}
    }
}



