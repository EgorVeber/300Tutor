package com.v_points.app.ui_core.components.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Моковые данные для превью
val mockGame = GameUiModel(
    id = "1",
    name = "Игра 1",
    scoreTeamOne = 25,
    scoreTeamTwo = 22,
    scoreText = "25 : 22"
)

// ==========================================
// ВАРИАНТ 1: Классическая (Акцент на счет)
// ==========================================
@Composable
fun GameCardClassic(
    game: GameUiModel,
    onClick: () -> Unit
) {
    // Определяем победителя
    val isTeamOneWinner = game.scoreTeamOne > game.scoreTeamTwo
    val isTeamTwoWinner = game.scoreTeamTwo > game.scoreTeamOne
    val isDraw = game.scoreTeamOne == game.scoreTeamTwo

    // Цвета для победителя и проигравшего
    val winnerColor = MaterialTheme.colorScheme.primary
    val loserColor = MaterialTheme.colorScheme.onSurfaceVariant

    val teamOneColor = if (isTeamOneWinner) winnerColor else loserColor
    val teamTwoColor = if (isTeamTwoWinner) winnerColor else loserColor

    val teamOneWeight = if (isTeamOneWinner) FontWeight.Bold else FontWeight.Medium
    val teamTwoWeight = if (isTeamTwoWinner) FontWeight.Bold else FontWeight.Medium

    val scoreOneWeight = if (isTeamOneWinner) FontWeight.Black else FontWeight.Bold
    val scoreTwoWeight = if (isTeamTwoWinner) FontWeight.Black else FontWeight.Bold

    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = game.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Команда 1
                Text(
                    text = game.teamOneName,
                    fontSize = 13.sp,
                    fontWeight = teamOneWeight,
                    color = teamOneColor
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = game.scoreTeamOne.toString(),
                    fontSize = 22.sp,
                    fontWeight = scoreOneWeight,
                    color = teamOneColor // Счет тоже красим в цвет победителя
                )

                // Разделитель
                Text(
                    text = " : ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                // Команда 2
                Text(
                    text = game.scoreTeamTwo.toString(),
                    fontSize = 22.sp,
                    fontWeight = scoreTwoWeight,
                    color = teamTwoColor // Счет тоже красим в цвет победителя
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = game.teamTwoName,
                    fontSize = 13.sp,
                    fontWeight = teamTwoWeight,
                    color = teamTwoColor
                )
            }
        }
    }
}


@Composable
fun GameCardClassicCompact(
    game: GameUiModel,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp), // Уменьшили вертикальный отступ снаружи
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp), // Уменьшили внутренние отступы
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Название игры (слева)
            Text(
                text = game.name,
                fontSize = 14.sp, // Уменьшили шрифт
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f) // Занимает свободное место, прижимая счет вправо
            )

            // Блок со счетом (справа)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // К1 и счет
                Text(
                    text = game.teamOneName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.scoreTeamOne.toString(),
                    fontSize = 18.sp, // Уменьшили счет
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )

                // Разделитель
                Text(
                    text = " : ",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                // Счет и К2
                Text(
                    text = game.scoreTeamTwo.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = game.teamTwoName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Классическая")
@Composable
fun PreviewGameCardClassic() {
    MaterialTheme {
        GameCardClassic(game = mockGame, onClick = {})
    }
}

// ==========================================
// ВАРИАНТ 2: С разделением на команды
// ==========================================
@Composable
fun GameCardSplit(
    game: GameUiModel,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = game.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 1-я команда
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Команда 1", fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text(
                    "${game.scoreTeamOne}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2-я команда
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.secondary)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Команда 2", fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text(
                    "${game.scoreTeamTwo}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "С разделением")
@Composable
fun PreviewGameCardSplit() {
    MaterialTheme {
        GameCardSplit(game = mockGame, onClick = {})
    }
}

// ==========================================
// ВАРИАНТ 3: Компактная
// ==========================================
@Composable
fun GameCardCompact(
    game: GameUiModel,
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
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = game.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            // Разделитель
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
                    .background(MaterialTheme.colorScheme.outlineVariant)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Используем готовый scoreText
            Text(
                text = game.scoreText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "Компактная")
@Composable
fun PreviewGameCardCompact() {
    MaterialTheme {
        Column {
            GameCardCompact(game = mockGame, onClick = {})
            GameCardCompact(
                game = mockGame.copy(
                    id = "2",
                    name = "Игра 2",
                    scoreTeamOne = 18,
                    scoreTeamTwo = 25,
                    scoreText = "18 : 25"
                ), onClick = {})
        }
    }
}

// ==========================================
// ОБЩЕЕ ПРЕВЬЮ ДЛЯ СРАВНЕНИЯ
// ==========================================
@Preview(showBackground = true, name = "Все варианты карточек игр", heightDp = 500)
@Composable
fun PreviewAllGameCards() {
    MaterialTheme {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                "Вариант 1: Классический",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameCardClassic(game = mockGame, onClick = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 4: Классический компактная",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameCardClassicCompact(game = mockGame, onClick = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 2: С разделением",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameCardSplit(game = mockGame, onClick = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Вариант 3: Компактный",
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            GameCardCompact(game = mockGame, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameCardClassicV2() {
    MaterialTheme {
        GameCardClassicV2(
            game = GameUiModel(
                id = "1",
                name = "Игра 1",
                scoreTeamOne = 25,
                scoreTeamTwo = 22,
                scoreText = "25 : 22",
                teamOneName = "К1",
                teamTwoName = "К2"
            ),
            onClick = {}
        )
    }
}

@Composable
fun GameCardClassicV2(
    game: GameUiModel,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Название игры (по центру, мелким шрифтом, как заголовок)
            Text(
                text = game.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Блок со счетом
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Команда 1 (Плашка)
                TeamScoreBadge(
                    teamName = game.teamOneName,
                    score = game.scoreTeamOne,
                    color = MaterialTheme.colorScheme.primary
                )

                // Разделитель (двоеточие)
                Text(
                    text = ":",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Команда 2 (Плашка)
                TeamScoreBadge(
                    teamName = game.teamTwoName,
                    score = game.scoreTeamTwo,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

// Компонент для отображения команды и ее счета
@Composable
private fun TeamScoreBadge(teamName: String, score: Int, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Название команды в плашке
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(color.copy(alpha = 0.15f))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = teamName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Счет
        Text(
            text = score.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = color
        )
    }
}