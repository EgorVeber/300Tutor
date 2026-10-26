package com.v_points.app.ui_core.components.menu_variants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuTile(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(contentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = contentColor, modifier = Modifier.size(24.dp))
            }
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5, widthDp = 360, heightDp = 420)
@Composable
fun MenuTilesPreview() {
    MaterialTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { MenuTile("Профиль", rememberVectorPainter(Icons.Default.Person)) }
            item {
                MenuTile(
                    "Заказы",
                    rememberVectorPainter(Icons.Default.Menu),
                    containerColor = Color(0xFFFFE9C7),
                    contentColor = Color(0xFF8A5300)
                )
            }
            item {
                MenuTile(
                    "Избранное",
                    rememberVectorPainter(Icons.Default.Favorite),
                    containerColor = Color(0xFFFCE4EC),
                    contentColor = Color(0xFF9C2550)
                )
            }
            item {
                MenuTile(
                    "Настройки",
                    rememberVectorPainter(Icons.Default.Settings),
                    containerColor = Color(0xFFE8EAF6),
                    contentColor = Color(0xFF303F9F)
                )
            }
        }
    }
}