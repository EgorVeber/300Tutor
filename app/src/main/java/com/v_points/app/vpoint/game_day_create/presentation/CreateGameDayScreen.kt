package com.v_points.app.vpoint.game_day_create.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v_points.app.ui_core.components.menu_variants.MenuListCard
import com.v_points.app.vpoint.fiersote.domain.GymModel

@Composable
fun CreateGameDayScreen(
    viewModel: CreateGameDayViewModel,
    onBackClick: () -> Unit
) {
    val state = viewModel.getState()

    CreateGameDayScreenContent(
        state = state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameDayScreenContent(
    modifier: Modifier = Modifier,
    state: CreateGameDayState,
    onAction: (CreateGameDayAction) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        TopAppBar(
            title = {
                Text(text = "Создать игровой день")
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
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MenuListCard(
                title = "Дата",
                subtitle = state.selectedDateText ?: "Нажмите, чтобы выбрать дату",
                imageVector = Icons.Default.DateRange,
                onClick = { onAction(CreateGameDayAction.OnDateClick) }
            )

            MenuListCard(
                title = "Зал",
                subtitle = state.selectedGymName ?: "Нажмите, чтобы выбрать зал",
                imageVector = Icons.Default.LocationOn,
                onClick = { onAction(CreateGameDayAction.OnGymClick) }
            )
        }

        Button(
            onClick = { onAction(CreateGameDayAction.OnCreateClick) },
            enabled = state.isCreateEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Создать", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        if (state.isDateDialogVisible) {
            DatePickerDialogComponent(
                onDateSelected = { millis ->
                    onAction(CreateGameDayAction.OnDateSelected(millis))
                },
                onDismiss = {
                    onAction(CreateGameDayAction.OnDismissDialogs)
                }
            )
        }

        if (state.isGymDialogVisible) {
            GymSelectionDialog(
                gyms = state.gyms,
                onGymSelected = { gym ->
                    onAction(CreateGameDayAction.OnGymSelected(gym))
                },
                onDismiss = {
                    onAction(CreateGameDayAction.OnDismissDialogs)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogComponent(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                }
            ) {
                Text("ОК")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun GymSelectionDialog(
    gyms: List<GymModel>,
    onGymSelected: (GymModel?) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите зал") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                gyms.forEach { gym ->
                    TextButton(
                        onClick = { onGymSelected(gym) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = gym.name.ifEmpty { gym.fullName })
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CreateGameDayScreenPreview() {
    MaterialTheme {
        CreateGameDayScreenContent(
            state = MutableCreateGameDayState().apply {
                selectedDateText = "02 ноября 2026"
                selectedGymName = "Спортзал на Ленина"
                isCreateEnabled = true
            },
            onAction = {},
            onBackClick = {}
        )
    }
}
