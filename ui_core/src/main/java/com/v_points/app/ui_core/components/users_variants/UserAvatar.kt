package com.v_points.app.ui_core.components.users_variants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// ════════════════════════════════════════════════════════════
// МОДЕЛЬ
// ════════════════════════════════════════════════════════════

data class UserStat(
    val label: String,
    val value: String
)

data class UserUiModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String? = null,
    val description: String? = null,
    val role: String? = null,
    val stats: List<UserStat> = emptyList()   // 👈 до 2 (или больше) метрик
)

private fun UserUiModel.fullName(): String = "$firstName $lastName"

// ════════════════════════════════════════════════════════════
// АВАТАР (общий)
// ════════════════════════════════════════════════════════════

@Composable
fun UserAvatar(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    size: Dp = 52.dp
) {
    if (!user.avatarUrl.isNullOrBlank()) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(size)
                .clip(CircleShape)
        )
    } else {
        val initials = "${user.firstName.firstOrNull() ?: ""}${user.lastName.firstOrNull() ?: ""}"
            .uppercase()
        Box(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                initials,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold,
                fontSize = (size.value / 2.5f).sp
            )
        }
    }
}

// ════════════════════════════════════════════════════════════
// 1. КЛАССИЧЕСКАЯ СТРОКА (card + arrow)
// ════════════════════════════════════════════════════════════

@Composable
fun UserRowClassic(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserAvatar(user)
            Column(Modifier.weight(1f)) {
                Text(user.fullName(), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ════════════════════════════════════════════════════════════
// 2. ВЕРТИКАЛЬНАЯ ПЛИТКА
// ════════════════════════════════════════════════════════════

@Composable
fun UserTileVertical(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .clickable { onClick(user.id) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserAvatar(user, size = 72.dp)
            Spacer(Modifier.height(12.dp))
            Text(
                user.fullName(),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            user.description?.let {
                Spacer(Modifier.height(2.dp))
                Text(
                    it,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ════════════════════════════════════════════════════════════
// 3. ДВУХСТРОЧНАЯ С ФОНОМ
// ════════════════════════════════════════════════════════════

@Composable
fun UserCardStacked(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(Modifier
            .fillMaxWidth()
            .padding(14.dp)) {
            Text(
                user.fullName(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                UserAvatar(user, size = 40.dp)
                user.description?.let {
                    Text(
                        it,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

// ════════════════════════════════════════════════════════════
// 4. ИМЯ + ЧИП РОЛИ
// ════════════════════════════════════════════════════════════

@Composable
fun UserRowWithRole(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserAvatar(user)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(user.fullName(), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                user.role?.let {
                    SuggestionChip(
                        onClick = {},
                        label = { Text(it, fontSize = 12.sp) }
                    )
                }
            }
        }
    }
}

// ════════════════════════════════════════════════════════════
// 5. КОМПАКТНАЯ СТРОКА (без Card)
// ════════════════════════════════════════════════════════════

@Composable
fun UserRowCompact(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) }
            .padding(horizontal = 4.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        UserAvatar(user, size = 36.dp)
        Text(
            user.fullName(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        user.description?.let {
            Text(
                it,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}

// ════════════════════════════════════════════════════════════
// 6. БАННЕР С ИНИЦИАЛАМИ
// ════════════════════════════════════════════════════════════

@Composable
fun UserCardBanner(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .width(88.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                val initials =
                    "${user.firstName.firstOrNull() ?: ""}${user.lastName.firstOrNull() ?: ""}"
                        .uppercase()
                Text(
                    initials,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(user.fullName(), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                user.description?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        it,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

// ════════════════════════════════════════════════════════════
// SAMPLE DATA
// ════════════════════════════════════════════════════════════

private fun sampleUsers(): List<UserUiModel> = listOf(
    UserUiModel(
        id = "1",
        firstName = "Анна",
        lastName = "Смирнова",
        description = "Преподаватель математики",
        role = "Учитель"
    ),
    UserUiModel(
        id = "2",
        firstName = "Иван",
        lastName = "Петров",
        description = "Студент, 3 курс",
        role = "Студент"
    ),
    UserUiModel(
        id = "3",
        firstName = "Мария",
        lastName = "Козлова",
        description = "Куратор направления",
        role = "Куратор"
    )
)

// ════════════════════════════════════════════════════════════
// PREVIEW: ВСЕ ВАРИАНТЫ
// ════════════════════════════════════════════════════════════

@Preview(
    name = "User rows — variants",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 380,
    heightDp = 900
)
@Composable
private fun UserRowsVariantsPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            val user = sampleUsers().first()

            Text("1. Классическая строка", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            UserRowClassic(user)

            Text("2. Вертикальная плитка", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                UserTileVertical(sampleUsers()[0])
                UserTileVertical(sampleUsers()[1])
            }

            Text("3. Двухстрочная с фоном", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            UserCardStacked(user)

            Text("4. Имя + чип роли", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            UserRowWithRole(user)

            Text("5. Компактная строка", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            UserRowCompact(user)

            Text("6. Баннер с инициалами", fontSize = 12.sp, fontWeight = FontWeight.Medium)
            UserCardBanner(user)
        }
    }
}

@Composable
fun UserCardWithStatsRight(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable { onClick(user.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserAvatar(user)

            Column(Modifier.weight(1f)) {
                Text(user.fullName(), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                user.description?.let {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        it,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (user.stats.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    user.stats.forEach { stat ->
                        StatBlock(stat)
                    }
                }
            }
        }
    }
}


@Composable
fun UserCardWithStatsBottom(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable { onClick(user.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                UserAvatar(user)
                Column(Modifier.weight(1f)) {
                    Text(user.fullName(), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    user.description?.let {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            it,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            if (user.stats.isNotEmpty()) {
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    user.stats.forEach { stat ->
                        Column {
                            Text(
                                stat.value,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                stat.label,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatBlock(stat: UserStat) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stat.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            stat.label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1
        )
    }
}

private fun sampleUsersWithStats(): List<UserUiModel> = listOf(
    UserUiModel(
        id = "1",
        firstName = "Анна",
        lastName = "Смирнова",
        description = "Преподаватель математики",
        role = "Учитель",
        stats = listOf(
            UserStat("Курсы", "12"),
            UserStat("Ученики", "146")
        )
    ),
    UserUiModel(
        id = "2",
        firstName = "Иван",
        lastName = "Петров",
        description = "Студент, 3 курс",
        role = "Студент",
        stats = listOf(
            UserStat("Курсы", "5"),
            UserStat("Баллы", "820")
        )
    )
)

@Preview(
    name = "User with stats — right",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 380,
    heightDp = 320
)
@Composable
private fun UserStatsRightPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            sampleUsersWithStats().forEach { user ->
                UserCardWithStatsRight(user)
            }
        }
    }
}

@Preview(
    name = "User with stats — bottom",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 380,
    heightDp = 420
)
@Composable
private fun UserStatsBottomPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            sampleUsersWithStats().forEach { user ->
                UserCardWithStatsBottom(user)
            }
        }
    }
}

@Preview(
    name = "User list — classic",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 380,
    heightDp = 520
)
@Composable
private fun UserListClassicPreview() {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sampleUsers(), key = { it.id }) { user ->
                UserRowClassic(user)
            }
        }
    }
}