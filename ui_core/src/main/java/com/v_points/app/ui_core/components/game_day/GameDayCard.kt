package com.v_points.app.ui_core.components.game_day


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ==========================================
// МОДЕЛЬ ДАННЫХ ДЛЯ UI
// ==========================================
data class GameDayUiModel(
    val id: String,
    val formattedDate: String,
    val gymName: String,
    val gamesCount: Int,
    val isUserParticipated: Boolean = false
)

// Моковые данные для превью
val mockDay = GameDayUiModel(
    id = "1",
    formattedDate = "02 Ноября 2026",
    gymName = "Спортзал на Ленина",
    gamesCount = 5,
    isUserParticipated = true
)

// ==========================================
// ВАРИАНТ 1: Классическая карточка (Material 3)
// ==========================================
@Composable
fun GameDayCardClassic(
    gameDay: GameDayUiModel,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = gameDay.formattedDate,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = gameDay.gymName,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Кружок с количеством игр (сдвигаем вправо)
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp) // Отступ от бейджа
                        .size(40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${gameDay.gamesCount}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Бейдж "Моя игра" - теперь он позиционируется перед кружком
            if (gameDay.isUserParticipated) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd) // Выравниваем по центру справа
                        .padding(end = 64.dp) // Сдвигаем влево, чтобы не перекрывать кружок (40dp кружок + 16dp отступ + 8dp запас)
                        .clip(RoundedCornerShape(50)) // Сделаем его круглым/овальным
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Моя",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Классическая (С бейджем)")
@Composable
fun PreviewGameDayCardClassicWithBadge() {
    MaterialTheme {
        GameDayCardClassic(gameDay = mockDay.copy(isUserParticipated = true), onClick = {})
    }
}

@Preview(showBackground = true, name = "Классическая (Без бейджа)")
@Composable
fun PreviewGameDayCardClassicNoBadge() {
    MaterialTheme {
        GameDayCardClassic(gameDay = mockDay.copy(isUserParticipated = false), onClick = {})
    }
}

// ==========================================
// ВАРИАНТ 2: Плитка с цветным акцентом
// ==========================================
@Composable
fun GameDayCardColorfulV2(
    gameDay: GameDayUiModel,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)) {
            // Цветная полоса слева
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.secondary)
            )
            // Контент
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = gameDay.formattedDate,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    // Бейдж
                    if (gameDay.isUserParticipated) {
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFFE8F5E9)) // Нежно-зеленый
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Вы играли",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32) // Темно-зеленый
                            )
                        }
                    }

                    Text(
                        text = "${gameDay.gamesCount} игр",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gameDay.gymName,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun GameDayCardColorful(
    gameDay: GameDayUiModel,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)) {
            // Цветная полоса слева
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.secondary)
            )

            // Основной контент
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // ВЕРХНЯЯ СТРОКА: Дата + Бейдж
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = gameDay.formattedDate,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // Бейдж "Ваша игра" сразу после даты
                    if (gameDay.isUserParticipated) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFFE8F5E9)) // Нежно-зеленый
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Вы участовали",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32) // Темно-зеленый
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // НИЖНЯЯ СТРОКА: Зал + Количество игр
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gameDay.gymName,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f) // Выталкивает счет вправо
                    )

                    // Количество игр
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SportsVolleyball,
                            contentDescription = "Игры",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${gameDay.gamesCount}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Цветная (С бейджем)")
@Composable
fun PreviewGameDayCardColorfulWithBadge() {
    MaterialTheme {
        GameDayCardColorful(gameDay = mockDay.copy(isUserParticipated = true), onClick = {})
    }
}

@Preview(showBackground = true, name = "Цветная (С бейджем)")
@Composable
fun PreviewGameDayCardColorfulWithBadgeV2() {
    MaterialTheme {
        GameDayCardColorfulV2(gameDay = mockDay.copy(isUserParticipated = true), onClick = {})
    }
}

@Preview(showBackground = true, name = "Цветная (Без бейджа)")
@Composable
fun PreviewGameDayCardColorfulNoBadge() {
    MaterialTheme {
        GameDayCardColorful(gameDay = mockDay.copy(isUserParticipated = false), onClick = {})
    }
}

// ==========================================
// ВАРИАНТ 3: Компактная карточка
// ==========================================
@Composable
fun GameDayCardCompact(
    gameDay: GameDayUiModel,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = gameDay.formattedDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            // Бейдж в компактной карточке
            if (gameDay.isUserParticipated) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Моя",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }

            // Разделитель
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
                    .background(MaterialTheme.colorScheme.outlineVariant)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.SportsVolleyball,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${gameDay.gamesCount}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Компактная (С бейджем)")
@Composable
fun PreviewGameDayCardCompactWithBadge() {
    MaterialTheme {
        GameDayCardCompact(gameDay = mockDay.copy(isUserParticipated = true), onClick = {})
    }
}

@Preview(showBackground = true, name = "Компактная (Без бейджа)")
@Composable
fun PreviewGameDayCardCompactNoBadge() {
    MaterialTheme {
        GameDayCardCompact(gameDay = mockDay.copy(isUserParticipated = false), onClick = {})
    }
}

// ==========================================
// ОБЩЕЕ ПРЕВЬЮ ДЛЯ СРАВНЕНИЯ (Все три варианта в колонке)
// ==========================================
@Preview(showBackground = true, name = "Все варианты вместе", heightDp = 600)
@Composable
fun PreviewAllGameDayCards() {
    MaterialTheme {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                "Вариант 1: Классический",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameDayCardClassic(gameDay = mockDay, onClick = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 2: Цветной",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameDayCardColorful(gameDay = mockDay, onClick = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 3: Компактный",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameDayCardCompact(gameDay = mockDay, onClick = {})
        }
    }
}