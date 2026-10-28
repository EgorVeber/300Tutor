package com.v_points.app.presentation.starter

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.v_points.app.R

enum class BottomTab {
    Games, Rating, Profile
}

@Composable
fun BottomNavBar(
    navController: NavController,
    onGamesClick: () -> Unit,
    onRatingClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationId = backStackEntry?.destination?.id

    val selectedTab = when (currentDestinationId) {
        R.id.mainFragment -> BottomTab.Games
        R.id.participantsFScreen -> BottomTab.Rating
        R.id.mainMenuFragment -> BottomTab.Profile
        else -> BottomTab.Games
    }

    BottomNavBarContent(
        selectedTab = selectedTab,
        onGamesClick = onGamesClick,
        onRatingClick = onRatingClick,
        onProfileClick = onProfileClick,
        modifier = modifier
    )
}

@Composable
fun BottomNavBarContent(
    selectedTab: BottomTab,
    onGamesClick: () -> Unit,
    onRatingClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor = MaterialTheme.colorScheme.primary
    val inactiveColor = MaterialTheme.colorScheme.secondary
    val backgroundColor = Color.White
    val borderColor = MaterialTheme.colorScheme.outlineVariant
    NavigationBar(
        modifier = modifier
            .height(56.dp)
            .drawWithContent {
                drawContent()
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, strokeWidth / 2f),
                    end = Offset(size.width, strokeWidth / 2f),
                    strokeWidth = strokeWidth
                )
            },
        containerColor = backgroundColor,
        tonalElevation = 0.dp,
        windowInsets = WindowInsets(0.dp)
    ) {
        NavigationBarItem(
            selected = selectedTab == BottomTab.Games,
            onClick = onGamesClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = "Игры",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Игры", fontSize = 10.sp) },
            colors = navItemColors(activeColor, inactiveColor)
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.Rating,
            onClick = onRatingClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Рейтинг",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Рейтинг", fontSize = 10.sp) },
            colors = navItemColors(activeColor, inactiveColor)
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.Profile,
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Профиль",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Профиль", fontSize = 10.sp) },
            colors = navItemColors(activeColor, inactiveColor)
        )
    }
}

@Composable
private fun navItemColors(
    activeColor: Color,
    inactiveColor: Color
) = NavigationBarItemDefaults.colors(
    selectedIconColor = activeColor,
    selectedTextColor = activeColor,
    unselectedIconColor = inactiveColor,
    unselectedTextColor = inactiveColor,
    indicatorColor = Color.Transparent
)

@Preview(showBackground = true)
@Composable
private fun BottomNavBarGamesPreview() {
    MaterialTheme {
        BottomNavBarContent(
            selectedTab = BottomTab.Games,
            onGamesClick = {},
            onRatingClick = {},
            onProfileClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarRatingPreview() {
    MaterialTheme {
        BottomNavBarContent(
            selectedTab = BottomTab.Rating,
            onGamesClick = {},
            onRatingClick = {},
            onProfileClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarProfilePreview() {
    MaterialTheme {
        BottomNavBarContent(
            selectedTab = BottomTab.Profile,
            onGamesClick = {},
            onRatingClick = {},
            onProfileClick = {}
        )
    }
}