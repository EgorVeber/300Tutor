package com.v_points.app.presentation.main_menu

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.v_points.app.core.UiCoreDrawable
import com.v_points.app.domain.account.models.AccountModel
import com.v_points.app.presentation.main_menu.MainMenuItem.Companion.getIcon
import com.v_points.app.presentation.main_menu.MainMenuItem.Companion.getTitle
import com.v_points.app.presentation.main_menu.adapter.MainMenuUiItem
import com.v_points.app.ui_core.components.menu_variants.MenuListCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel,
    onAccountClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val account by viewModel.getAccountStateFlow().collectAsState(initial = AccountModel.EMPTY)
    val items by viewModel.getUiItemStateFlow().collectAsState()

    MainMenuScreenContent(
        account = account,
        items = items,
        onAccountClick = onAccountClick,
        onSettingsClick = onSettingsClick,
        onItemClick = { uiItem ->
            viewModel.onMenuItemClicked(uiItem)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreenContent(
    account: AccountModel,
    items: List<MainMenuUiItem>,
    onAccountClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onItemClick: (MainMenuUiItem) -> Unit
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val view = LocalView.current
    SideEffect {
        val window = (view.context as? Activity)?.window
        window?.statusBarColor = surfaceColor.toArgb()
        if (window != null) {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surfaceColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onAccountClick)
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${account.name} ${account.surname}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            if (account.email.isNotEmpty()) {
                                Text(
                                    text = account.email,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            painter = painterResource(id = UiCoreDrawable.ic_setting_app),
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items.forEach { uiItem ->
                    val title = stringResource(id = uiItem.mainMenuItem.getTitle())
                    val icon = painterResource(id = uiItem.mainMenuItem.getIcon())
                    MenuListCard(
                        title = title,
                        subtitle = null,
                        icon = icon,
                        onClick = {
                            onItemClick(uiItem)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "MainMenuScreen Preview")
@Composable
fun MainMenuScreenPreview() {
    MaterialTheme {
        MainMenuScreenContent(
            account = AccountModel.EMPTY.copy(name = "Иван", surname = "Иванов", email = "ivan@test.com"),
            items = listOf(
                MainMenuUiItem(MainMenuItem.SCHEDULE),
                MainMenuUiItem(MainMenuItem.NOTES),
                MainMenuUiItem(MainMenuItem.FAVORITES_QUESTION)
            ),
            onAccountClick = {},
            onSettingsClick = {},
            onItemClick = {}
        )
    }
}
