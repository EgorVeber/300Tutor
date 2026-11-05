package com.v_points.app.ui_core.components.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GamesListCardClassic(
    games: List<GameUiModel>,
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
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            // === ШАПКА ===
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Слева: Заголовок
                Text(
                    text = "Игры дня",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                // Справа: Названия команд над счетом
                Row(
                    modifier = Modifier.width(110.dp), // Ширина блока счета
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = games.firstOrNull()?.teamOneName ?: "К1",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = games.firstOrNull()?.teamTwoName ?: "К2",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // === СПИСОК ИГР ===
            games.forEachIndexed { index, game ->
                val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
                val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Левая часть: Название игры
                    Text(
                        text = game.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )

                    // Правая часть: Счет
                    Row(
                        modifier = Modifier.width(110.dp), // Такая же ширина, как у шапки
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Счет К1
                        Text(
                            text = game.scoreTeamOne.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )

                        // Счет К2
                        Text(
                            text = game.scoreTeamTwo.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Разделитель между играми (кроме последней)
                if (index < games.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
private fun GameRowClassic(game: GameUiModel) {
    val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
    val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Название игры
        Text(
            text = game.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )

        // Счет
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = game.teamOneName,
                fontSize = 12.sp,
                fontWeight = if (isTeamOneWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = game.scoreTeamOne.toString(),
                fontSize = 16.sp,
                fontWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Normal,
                color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                " : ",
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Text(
                text = game.scoreTeamTwo.toString(),
                fontSize = 16.sp,
                fontWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Normal,
                color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = game.teamTwoName,
                fontSize = 12.sp,
                fontWeight = if (isTeamTwoWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun GamesListCardGrid(
    games: List<GameUiModel>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Игры дня",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Располагаем игры в 2 колонки
            games.chunked(2).forEach { rowGames ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowGames.forEach { game ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(8.dp)
                        ) {
                            GameGridItem(game)
                        }
                    }
                    // Если игр нечетное количество, добавляем пустое место
                    if (rowGames.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun GameGridItem(game: GameUiModel) {
    val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
    val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne

    Column {
        Text(
            text = game.name,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = game.teamOneName,
                fontSize = 11.sp,
                fontWeight = if (isTeamOneWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = " ${game.scoreTeamOne}",
                fontSize = 15.sp,
                fontWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Normal,
                color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            Text(" : ", color = MaterialTheme.colorScheme.outline, fontSize = 12.sp)
            Text(
                text = "${game.scoreTeamTwo} ",
                fontSize = 15.sp,
                fontWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Normal,
                color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = game.teamTwoName,
                fontSize = 11.sp,
                fontWeight = if (isTeamTwoWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun GamesListCardMinimal(
    games: List<GameUiModel>,
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
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            // ШАПКА
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "Игры дня",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Заголовки колонок
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Пустое место над названиями игр
                    Spacer(modifier = Modifier.weight(1f))

                    // Заголовки команд над счетом
                    Row(
                        modifier = Modifier.width(100.dp), // Фиксированная ширина для блока счета
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = games.firstOrNull()?.teamOneName ?: "К1",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = games.firstOrNull()?.teamTwoName ?: "К2",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Список игр
            games.forEach { game ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Название игры (слева)
                    Text(
                        text = game.name,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    // Счет (справа)
                    Row(
                        modifier = Modifier.width(100.dp), // Такая же ширина, как у заголовков
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = game.scoreTeamOne.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = game.scoreTeamTwo.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Вариант 1: Списком")
@Composable
fun PreviewV1() {
    MaterialTheme { GamesListCardClassic(mockGamesList) }
}

@Preview(showBackground = true, name = "Вариант 2: Плиткой")
@Composable
fun PreviewV2() {
    MaterialTheme { GamesListCardGrid(mockGamesList) }
}

@Preview(showBackground = true, name = "Вариант 3: Минимализм")
@Composable
fun PreviewV3() {
    MaterialTheme { GamesListCardMinimal(mockGamesList) }
}

// Моки
val mockGamesList = listOf(
    GameUiModel("1", "Игра 1", "25 : 22", 25, 22, "К1", "К2"),
    GameUiModel("2", "Игра 2", "18 : 25", 18, 25, "К1", "К2"),
    GameUiModel("3", "Игра 3", "22 : 22", 22, 22, "К1", "К2"),
    GameUiModel("4", "Игра 4", "15 : 10", 15, 10, "К1", "К2"),
    GameUiModel("5", "Игра 5", "20 : 18", 20, 18, "К1", "К2")
)

@Composable
fun ResultBlockStrict(
    teamOneWins: Int,
    teamTwoWins: Int,
    teamOneName: String,
    teamTwoName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant) // Легкий серый фон
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = teamOneName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = teamOneWins.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = " : ",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = teamTwoWins.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = teamTwoName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
fun ResultBlockWinner(
    teamOneWins: Int,
    teamTwoWins: Int,
    teamOneName: String,
    teamTwoName: String
) {
    val isTeamOneWinner = teamOneWins > teamTwoWins
    val isTeamTwoWinner = teamTwoWins > teamOneWins
    val winnerColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom // Прижимаем к низу
    ) {
        // --- К1 ---
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = teamOneName,
                fontSize = 14.sp,
                fontWeight = if (isTeamOneWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamOneWinner) winnerColor else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = teamOneWins.toString(),
                fontSize = 24.sp, // Вернули нормальный размер
                fontWeight = FontWeight.Black,
                color = if (isTeamOneWinner) winnerColor else MaterialTheme.colorScheme.onSurface
            )
        }

        // --- Разделитель ---
        Text(
            text = ":",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        // --- К2 ---
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = teamTwoWins.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = if (isTeamTwoWinner) winnerColor else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = teamTwoName,
                fontSize = 14.sp,
                fontWeight = if (isTeamTwoWinner) FontWeight.Bold else FontWeight.Normal,
                color = if (isTeamTwoWinner) winnerColor else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ResultBlockBadge(teamOneWins: Int, teamTwoWins: Int, teamOneName: String, teamTwoName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50)) // Закругленные края
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = teamOneName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$teamOneWins : $teamTwoWins",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = teamTwoName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun GamesListCardFinal(
    games: List<GameUiModel>,
    modifier: Modifier = Modifier
) {
    // Считаем победы
    val teamOneWins = games.count { it.scoreTeamOne > it.scoreTeamTwo }
    val teamTwoWins = games.count { it.scoreTeamTwo > it.scoreTeamOne }

    val teamOneName = games.firstOrNull()?.teamOneName ?: "К1"
    val teamTwoName = games.firstOrNull()?.teamTwoName ?: "К2"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {

            // === ШАПКА ===
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Игры дня",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                // Названия команд в шапке (нейтральный цвет)
                Row(
                    modifier = Modifier.width(110.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = teamOneName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium, // Не жирный
                        color = MaterialTheme.colorScheme.onSurfaceVariant, // Нейтральный
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = teamTwoName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium, // Не жирный
                        color = MaterialTheme.colorScheme.onSurfaceVariant, // Нейтральный
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // === СПИСОК ИГР ===
            games.forEachIndexed { index, game ->
                val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
                val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = game.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        modifier = Modifier.width(110.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = game.scoreTeamOne.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = game.scoreTeamTwo.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (index < games.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }

            // === БЛОК РЕЗУЛЬТАТА ===
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            ResultBlockWinner(
                teamOneWins = teamOneWins,
                teamTwoWins = teamTwoWins,
                teamOneName = teamOneName,
                teamTwoName = teamTwoName
            )
        }
    }
}

@Composable
fun GamesListContent(
    games: List<GameUiModel>,
    modifier: Modifier = Modifier,
    resultBlock: @Composable (Int, Int, String, String) -> Unit
) {
    val teamOneWins = games.count { it.scoreTeamOne > it.scoreTeamTwo }
    val teamTwoWins = games.count { it.scoreTeamTwo > it.scoreTeamOne }
    val teamOneName = games.firstOrNull()?.teamOneName ?: "К1"
    val teamTwoName = games.firstOrNull()?.teamTwoName ?: "К2"

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Шапка
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Игры дня",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.width(110.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        teamOneName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        teamTwoName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // Список
            games.forEachIndexed { index, game ->
                val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
                val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        game.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        modifier = Modifier.width(110.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            game.scoreTeamOne.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamOneWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            game.scoreTeamTwo.toString(),
                            fontSize = 16.sp,
                            fontWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Normal,
                            color = if (isTeamTwoWinner) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                if (index < games.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }

            // Блок результата
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            resultBlock(teamOneWins, teamTwoWins, teamOneName, teamTwoName)
        }
    }
}

@Composable
fun GamesListCardV1(games: List<GameUiModel>) {
    GamesListContent(games) { w1, w2, n1, n2 -> ResultBlockStrict(w1, w2, n1, n2) }
}

@Composable
fun GamesListCardV2(games: List<GameUiModel>) {
    GamesListContent(games) { w1, w2, n1, n2 -> ResultBlockWinner(w1, w2, n1, n2) }
}

@Composable
fun GamesListCardV3(games: List<GameUiModel>) {
    GamesListContent(games) { w1, w2, n1, n2 -> ResultBlockBadge(w1, w2, n1, n2) }
}

@Composable
fun GamesListCardV4(modifier: Modifier = Modifier, games: List<GameUiModel>) {
    GamesListContent(games, modifier) { w1, w2, n1, n2 ->
        ResultBlockWinnerWithLabel(
            w1,
            w2,
            n1,
            n2
        )
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
fun PreviewAllGameCardsList() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 8.dp)
        ) {
            Text(
                "Вариант 1: Строгий",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GamesListCardV1(mockGamesList)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 2: Победный",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GamesListCardV2(mockGamesList)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 3: Плашка",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GamesListCardV3(mockGamesList)

            Text(
                "Вариант 4: 4",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GamesListCardV4(modifier = Modifier.padding(16.dp), mockGamesList)
        }
    }
}


@Composable
fun ResultBlockWinnerWithLabel(
    teamOneWins: Int,
    teamTwoWins: Int,
    teamOneName: String,
    teamTwoName: String
) {
    val isTeamOneWinner = teamOneWins > teamTwoWins
    val isTeamTwoWinner = teamTwoWins > teamOneWins
    val winnerColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // --- Левая часть: "Итог" (занимает всё свободное место) ---
        Text(
            text = "Итог",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold, // Сделал жирным, чтобы выделялось
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        // --- Правая часть: Блок со счетом (фиксированная ширина 110.dp, как в шапке) ---
        Row(
            modifier = Modifier.width(110.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Счет К1
            Text(
                text = teamOneWins.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = if (isTeamOneWinner) winnerColor else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            // Счет К2
            Text(
                text = teamTwoWins.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = if (isTeamTwoWinner) winnerColor else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}