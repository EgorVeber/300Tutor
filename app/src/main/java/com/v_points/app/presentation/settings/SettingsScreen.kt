package com.v_points.app.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.ui_core.components.menu_variants.MenuListCard

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit,
) {
    SettingsScreenContent(
        onBackClick = onBackClick,
        onGameDayClick = {
            viewModel.onGameDayClick()
        },
        onGameClick = {
            viewModel.onGameClick()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    onBackClick: () -> Unit,
    onGameDayClick: () -> Unit,
    onGameClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = UiCoreStrings.settings))
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
                title = "Создать игровой день",
                subtitle = null,
                imageVector = Icons.Default.SportsScore,
                onClick = onGameDayClick
            )
            MenuListCard(
                title = "Создать игру",
                subtitle = null,
                imageVector = Icons.Default.SportsVolleyball,
                onClick = onGameClick
            )
        }
    }
}

@Preview(showBackground = true, name = "Settings with School")
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreenContent(
            onBackClick = {},
            onGameDayClick = {},
            onGameClick = {},
        )
    }
}
