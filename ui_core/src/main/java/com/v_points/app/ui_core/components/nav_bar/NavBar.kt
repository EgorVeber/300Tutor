package com.v_points.app.ui_core.components.nav_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavBar(
    title: String,
    subtitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Левый слот: back button или пустое место-заглушка
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                if (onBackClick != null) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            }

            // Центральный слот: заголовок + подзаголовок
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Правый слот: иконка меню или заглушка для симметрии
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                if (onMenuClick != null) {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Меню"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "1. Только заголовок", widthDp = 360)
@Composable
private fun NavBarPreview_TitleOnly() {
    MaterialTheme {
        NavBar(title = "Профиль")
    }
}

@Preview(showBackground = true, name = "2. Заголовок + подзаголовок", widthDp = 360)
@Composable
private fun NavBarPreview_TitleSubtitle() {
    MaterialTheme {
        NavBar(
            title = "Профиль",
            subtitle = "Настройки аккаунта"
        )
    }
}

@Preview(showBackground = true, name = "3. С кнопкой назад", widthDp = 360)
@Composable
private fun NavBarPreview_BackOnly() {
    MaterialTheme {
        NavBar(
            title = "Детали заказа",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, name = "4. С иконкой меню", widthDp = 360)
@Composable
private fun NavBarPreview_MenuOnly() {
    MaterialTheme {
        NavBar(
            title = "Главная",
            onMenuClick = {}
        )
    }
}

@Preview(showBackground = true, name = "5. Назад + меню", widthDp = 360)
@Composable
private fun NavBarPreview_BothButtons() {
    MaterialTheme {
        NavBar(
            title = "Главная",
            onBackClick = {},
            onMenuClick = {}
        )
    }
}

@Preview(showBackground = true, name = "6. Полный вариант", widthDp = 360)
@Composable
private fun NavBarPreview_Full() {
    MaterialTheme {
        NavBar(
            title = "Заказ №123",
            subtitle = "В пути · 2 дня",
            onBackClick = {},
            onMenuClick = {}
        )
    }
}

@Preview(showBackground = true, name = "7. Длинный текст (ellipsis)", widthDp = 360)
@Composable
private fun NavBarPreview_LongText() {
    MaterialTheme {
        NavBar(
            title = "Очень длинное название экрана, которое не помещается",
            subtitle = "И не менее длинный подзаголовок для проверки обрезки",
            onBackClick = {},
            onMenuClick = {}
        )
    }
}

/**
 * Сводное превью: все варианты в одной колонке.
 * Удобно, чтобы увидеть, что центр заголовка не смещается
 * при появлении/исчезновении боковых кнопок.
 */
@Preview(showBackground = true, name = "8. Все варианты вместе", widthDp = 360, heightDp = 640)
@Composable
private fun NavBarPreview_AllVariants() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavBar(title = "1. Только заголовок")
            Spacer(Modifier.height(8.dp))

            NavBar(title = "2. + подзаголовок", subtitle = "subtitle")
            Spacer(Modifier.height(8.dp))

            NavBar(title = "3. Назад", onBackClick = {})
            Spacer(Modifier.height(8.dp))

            NavBar(title = "4. Меню", onMenuClick = {})
            Spacer(Modifier.height(8.dp))

            NavBar(title = "5. Назад + меню", onBackClick = {}, onMenuClick = {})
            Spacer(Modifier.height(8.dp))

            NavBar(
                title = "6. Полный",
                subtitle = "с подзаголовком",
                onBackClick = {},
                onMenuClick = {}
            )
        }
    }
}