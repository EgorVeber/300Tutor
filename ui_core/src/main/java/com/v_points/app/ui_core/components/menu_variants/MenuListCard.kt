package com.v_points.app.ui_core.components.menu_variants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuListCard(
    title: String,
    subtitle: String?,
    icon: Painter,
    badge: String? = null,
    shoNextIcon: Boolean = true,
    showSuccessIcon: Boolean = false,     // новая опция
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(Modifier.weight(1f)) {
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                if (subtitle != null) {
                    Text(
                        subtitle,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (badge != null) {
                Badge(containerColor = MaterialTheme.colorScheme.error) {
                    Text(badge, modifier = Modifier.padding(horizontal = 6.dp))
                }
            }

            // Зелёная галочка успеха (приоритетнее стрелки)
            if (showSuccessIcon) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Успешно",
                    tint = SuccessGreen,
                    modifier = Modifier.size(22.dp)
                )
            } else if (shoNextIcon) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// Зелёный цвет — вынесите в тему/палитру
private val SuccessGreen = Color(0xFF4CAF50)

@Composable
fun MenuListCard(
    title: String,
    subtitle: String?,
    imageVector: ImageVector,
    badge: String? = null,
    shoNextIcon: Boolean = true,
    showSuccessIcon: Boolean = false,
    onClick: () -> Unit = {}
) {
    MenuListCard(
        title = title,
        subtitle = subtitle,
        shoNextIcon = shoNextIcon,
        icon = rememberVectorPainter(imageVector),
        badge = badge,
        onClick = onClick,
        showSuccessIcon = false
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5, widthDp = 360, heightDp = 440)
@Composable
fun MenuListPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MenuListCard(
                "Профиль", "Личные данные",
                imageVector = Icons.Default.Person
            )
            MenuListCard(
                "Уведомления", "5 непрочитанных",
                imageVector = Icons.Default.Notifications,
                badge = "5"
            )
            MenuListCard(
                "Избранное", "12 товаров",
                rememberVectorPainter(Icons.Default.Favorite)
            )
            MenuListCard(
                "Помощь", "FAQ и поддержка",
                rememberVectorPainter(Icons.Default.Home)
            )
            MenuListCard(
                "Выход", null,
                rememberVectorPainter(Icons.Default.ExitToApp),
                showSuccessIcon = true
            )
        }
    }
}
