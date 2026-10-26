package com.v_points.app.ui_core.components.menu_item

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
fun MainMenuItem(
    modifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackground: Color = MaterialTheme.colorScheme.primaryContainer,
    mainMenuUiModel: MainMenuUiModel,
    showArrow: Boolean = true,
    onClick: () -> Unit = {}
) {

    val painter = rememberVectorPainter(
        when (mainMenuUiModel) {
            is MainMenuUiModel.Games -> Icons.Default.VerifiedUser
            is MainMenuUiModel.Users -> Icons.Default.SupervisedUserCircle
            is MainMenuUiModel.Profile -> Icons.Default.Help
        }
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Круглая иконка из drawable
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = if (mainMenuUiModel.iconRes != null) painterResource(id = mainMenuUiModel.iconRes!!) else painter,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = mainMenuUiModel.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (mainMenuUiModel.subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = mainMenuUiModel.subtitle!!,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(
    name = "MenuCard",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    showSystemUi = false
)
@Composable
fun MenuCardPreview() {          // <- убрали private
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MainMenuItem(
                mainMenuUiModel = MainMenuUiModel.Games(
                    title = "Профиль",
                    subtitle = "Настройки аккаунта",
                    iconRes = null,
                ),
                onClick = {}
            )
            MainMenuItem(
                mainMenuUiModel = MainMenuUiModel.Users(
                    title = "Приватность",
                    iconRes = null,
                    subtitle = null
                ),
                onClick = {}
            )
            MainMenuItem(
                mainMenuUiModel = MainMenuUiModel.Profile(
                    title = "Выход",
                    iconRes = null,
                    subtitle = null
                ),
                iconTint = Color(0xFFB3261E),
                iconBackground = Color(0xFFF9DEDC),
                showArrow = false,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5, heightDp = 500)
@Composable
private fun MenuScreenPreview() {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                listOf(
                    MainMenuUiModel.Games(
                        "Профиль",
                        "Настройки аккаунта",
                        R.drawable.ic_menu_manage
                    ),
                    MainMenuUiModel.Games(
                        "Уведомления",
                        "Звук и вибрация",
                        R.drawable.ic_popup_reminder
                    ),
                    MainMenuUiModel.Games("Приватность", null, R.drawable.ic_lock_lock),
                    MainMenuUiModel.Games(
                        "Помощь",
                        "FAQ и поддержка",
                        R.drawable.ic_menu_help
                    ),
                )
            ) { item ->
                MainMenuItem(
                    mainMenuUiModel = item,
                    onClick = {}
                )
            }
        }
    }
}