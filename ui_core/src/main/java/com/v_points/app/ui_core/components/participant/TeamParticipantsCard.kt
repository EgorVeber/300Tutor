package com.v_points.app.ui_core.components.participant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val mockParticipantsWithRatingWithRating = TeamParticipantsUiModel(
    teamOneName = "К1",
    teamTwoName = "К2",
    teamOnePlayers = listOf(
        PlayerUiModel("Иван", 67), PlayerUiModel("Вячеслав", 72),
        PlayerUiModel("Максим", 80), PlayerUiModel("Дима", 55),
        PlayerUiModel("Егор", 90), PlayerUiModel("Вадим", 61)
    ),
    teamTwoPlayers = listOf(
        PlayerUiModel("Евгений", 65), PlayerUiModel("Юля", 78),
        PlayerUiModel("Света", 45), PlayerUiModel("Саша", 88),
        PlayerUiModel("Дарья", 70), PlayerUiModel("Олеся", 59)
    )
)

// ==========================================
// ВАРИАНТ 1: Классический (Две колонки с маркерами)
// ==========================================
@Composable
fun ParticipantsCardClassic(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Составы команд",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Команда 1
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeader(
                        name = participants.teamOneName,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamOnePlayers.forEach { playerName ->
                        PlayerRowClassic(
                            playerName = playerName.name,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }

                // Разделитель
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(1.dp)
                        .height(IntrinsicSize.Max)
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )

                // Команда 2
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeader(
                        name = participants.teamTwoName,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamTwoPlayers.forEach { playerName ->
                        PlayerRowClassic(
                            playerName = playerName.name,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun TeamHeader(name: String, color: Color) {
    Text(
        text = name,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
private fun PlayerRowClassic(playerName: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = playerName, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview(showBackground = true, name = "Вариант 1: Классический")
@Composable
fun PreviewParticipantsClassic() {
    MaterialTheme { ParticipantsCardClassic(mockParticipantsWithRatingWithRating) }
}

// ==========================================
// ВАРИАНТ 2: Список (Строгий, вертикальный)
// ==========================================
@Composable
fun ParticipantsCardList(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Команда 1
            Text(
                text = participants.teamOneName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = participants.teamOnePlayers.joinToString(", ") { it.name },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(16.dp))

            // Команда 2
            Text(
                text = participants.teamTwoName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = participants.teamTwoPlayers.joinToString(", ") { it.name },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, name = "Вариант 2: Список")
@Composable
fun PreviewParticipantsList() {
    MaterialTheme { ParticipantsCardList(mockParticipantsWithRatingWithRating) }
}

// ==========================================
// ВАРИАНТ 3: Плитка (С аватарами-заглушками)
// ==========================================
@Composable
fun ParticipantsCardAvatars(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Команда 1
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = participants.teamOneName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                participants.teamOnePlayers.forEach { name ->
                    PlayerAvatarRow(name.name, MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Команда 2
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = participants.teamTwoName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                participants.teamTwoPlayers.forEach { name ->
                    PlayerAvatarRow(name.name, MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun PlayerAvatarRow(name: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Кружок с первой буквой
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1).uppercase(), // Первая буква
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, name = "Вариант 3: Аватары")
@Composable
fun PreviewParticipantsAvatars() {
    MaterialTheme { ParticipantsCardAvatars(mockParticipantsWithRatingWithRating) }
}

@Preview(showBackground = true, name = "Вариант 3: Рейтинг вместо буквы")
@Composable
fun PreviewParticipantsAvatarsWithRating() {
    MaterialTheme {
        ParticipantsCardAvatarsWithRating(mockParticipantsWithRatingWithRating)
    }
}

@Composable
fun ParticipantsCardAvatarsWithRating(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Заголовок
            Text(
                text = "Составы команд",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Команда 1
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = participants.teamOneName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    participants.teamOnePlayers.forEach { player ->
                        PlayerRowWithRating(player, MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Команда 2
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = participants.teamTwoName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    participants.teamTwoPlayers.forEach { player ->
                        PlayerRowWithRating(player, MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerRowWithRating(player: PlayerUiModel, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Кружок с рейтингом (вместо буквы)
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f)), // Светлый фон
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.rating.toString(), // Число рейтинга
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = color // Темный цвет команды для контраста
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Имя игрока
        Text(
            text = player.name,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )
    }
}

// ==========================================
// ОБЩЕЕ ПРЕВЬЮ ДЛЯ СРАВНЕНИЯ
// ==========================================
@Preview(
    showBackground = true,
    name = "Все варианты карточек участников",
    heightDp = 1200
)
@Composable
fun PreviewAllParticipantsCards() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Вариант 1: Классический",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            ParticipantsCardClassic(mockParticipantsWithRatingWithRating)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 2: Список",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            ParticipantsCardList(mockParticipantsWithRatingWithRating)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 3: Аватары",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            ParticipantsCardAvatars(mockParticipantsWithRatingWithRating)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 4: Аватары Рейтинг",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            ParticipantsCardAvatarsWithRating(mockParticipantsWithRatingWithRating)
        }
    }
}


@Composable
fun ParticipantsCardCompact(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp), // Уменьшили внешний отступ
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) { // Уменьшили внутренний отступ с 16 до 12

            Text(
                text = "Составы команд",
                fontSize = 14.sp, // Уменьшили заголовок
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp) // Меньше отступ
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Команда 1
                Column(modifier = Modifier.weight(1f)) {
                    TeamBadge(
                        name = participants.teamOneName,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamOnePlayers.forEach { player ->
                        PlayerRowCompact(player, MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(6.dp)) // Уменьшили отступ между игроками
                    }
                }

                // Команда 2
                Column(modifier = Modifier.weight(1f)) {
                    TeamBadge(
                        name = participants.teamTwoName,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamTwoPlayers.forEach { player ->
                        PlayerRowCompact(player, MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun TeamBadge(name: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(vertical = 4.dp), // Плашка стала тоньше
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 13.sp, // Меньше шрифт
            fontWeight = FontWeight.ExtraBold,
            color = color
        )
    }
}

@Composable
private fun PlayerRowCompact(player: PlayerUiModel, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Уменьшенный аватар с рейтингом
        Box(
            modifier = Modifier
                .size(26.dp) // Уменьшили с 32 до 26
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.rating.toString(),
                fontSize = 11.sp, // Уменьшили шрифт рейтинга
                fontWeight = FontWeight.Black,
                color = color
            )
        }

        Spacer(modifier = Modifier.width(6.dp)) // Меньше отступ

        // Имя игрока (с обрезкой, если слишком длинное)
        Text(
            text = player.name,
            fontSize = 12.sp, // Уменьшили шрифт имени
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis // Если имя длинное — поставит ...
        )
    }
}

val mockParticipantsCompact = TeamParticipantsUiModel(
    teamOneName = "К1",
    teamTwoName = "К2",
    teamOnePlayers = listOf(
        PlayerUiModel(
            "Иван",
            67,
        ), PlayerUiModel(
            "Вячеслав",
            72
        ),
        PlayerUiModel(
            "Максим",
            80,
        ), PlayerUiModel("Дима", 55),
        PlayerUiModel(
            "Егор",
            90,
        ), PlayerUiModel("Вадим", 61)
    ),
    teamTwoPlayers = listOf(
        PlayerUiModel("Евгений", 65), PlayerUiModel("Юля", 78),
        PlayerUiModel("Света", 45), PlayerUiModel("Саша", 88),
        PlayerUiModel("Дарья", 70), PlayerUiModel("Олеся", 59)
    )
)


@Preview(showBackground = true)
@Composable
fun PreviewParticipantsCompact() {
    MaterialTheme {
        ParticipantsCardCompact(mockParticipantsCompact)
    }
}


@Composable
fun ParticipantsCardCompactV2(
    participants: TeamParticipantsUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = "Составы команд",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Отступ между колонками
            ) {
                // Команда 1
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeaderLabel(name = participants.teamOneName)
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamOnePlayers.forEach { player ->
                        PlayerRowCompact(player)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }

                // Команда 2
                Column(modifier = Modifier.weight(1f)) {
                    TeamHeaderLabel(name = participants.teamTwoName)
                    Spacer(modifier = Modifier.height(8.dp))
                    participants.teamTwoPlayers.forEach { player ->
                        PlayerRowCompact(player)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}

// Просто текстовый заголовок, без цвета и фона
@Composable
private fun TeamHeaderLabel(name: String) {
    Text(
        text = name,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant, // Серый цвет
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PlayerRowCompact(player: PlayerUiModel) {
    // Определяем цвета в зависимости от того, текущий это юзер или нет
    val backgroundColor = Color.Transparent
    val textColor =
        if (player.isCurrentUser) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
    val ratingBgColor = MaterialTheme.colorScheme.surfaceVariant
    val ratingTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor) // Подсветка фона всей строки (если текущий юзер)
            .padding(horizontal = 4.dp, vertical = 2.dp) // Небольшой отступ внутри строки
    ) {
        // Аватар с рейтингом
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(ratingBgColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.rating.toString(),
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                color = ratingTextColor
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Имя игрока
        Text(
            text = player.name,
            fontSize = 13.sp,
            fontWeight = if (player.isCurrentUser) FontWeight.Bold else FontWeight.Normal,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParticipantsCompactV2() {
    MaterialTheme {
        ParticipantsCardCompactV2(mockParticipantsCompact, Modifier.padding(16.dp))
    }
}