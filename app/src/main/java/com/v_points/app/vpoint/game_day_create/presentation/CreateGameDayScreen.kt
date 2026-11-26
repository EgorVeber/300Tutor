package com.v_points.app.vpoint.game_day_create.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            .navigationBarsPadding()
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
                shoNextIcon = false,
                onClick = { onAction(CreateGameDayAction.OnDateClick) }
            )

            MenuListCard(
                title = "Зал",
                shoNextIcon = false,
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = gym.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (gym.fullName.isNotEmpty() && gym.fullName != gym.name) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = gym.fullName,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
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

@Preview(showBackground = true, name = "Date Picker Dialog Preview")
@Composable
fun DatePickerDialogPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DatePickerDialogComponent(
                onDateSelected = {},
                onDismiss = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Gym Selection Dialog Preview")
@Composable
fun GymSelectionDialogPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            GymSelectionDialog(
                gyms = listOf(
                    GymModel("1", "Зал №1", "Спортивный зал №1 на Ленина", 0.0, 0.0),
                    GymModel("2", "Зал №2", "Физкультурный комплекс на Победы", 0.0, 0.0)
                ),
                onGymSelected = {},
                onDismiss = {}
            )
        }
    }
}
