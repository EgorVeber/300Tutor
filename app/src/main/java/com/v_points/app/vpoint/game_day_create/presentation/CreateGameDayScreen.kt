package com.v_points.app.vpoint.game_day_create.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.ui_core.components.menu_variants.MenuListCard

@Composable
fun CreateGameDayScreen(
    viewModel: CreateGameDayViewModel,
    onBackClick: () -> Unit
) {
    CreateGameDayScreenContent(
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameDayScreenContent(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MenuListCard(
                title = "Добавить игровой день",
                subtitle = "Нажмите для создания",
                imageVector = Icons.Default.Add,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGameDayScreenPreview() {
    MaterialTheme {
        CreateGameDayScreenContent(onBackClick = {})
    }
}
