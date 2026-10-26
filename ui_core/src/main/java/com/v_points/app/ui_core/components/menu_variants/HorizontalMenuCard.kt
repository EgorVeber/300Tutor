package com.v_points.app.ui_core.components.menu_variants

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.graphics.Brush

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HorizontalMenuCard(
    title: String,
    subtitle: String,
    icon: Painter,
    gradient: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(gradient))
                .padding(16.dp)
        ) {
            Icon(
                icon, null,
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopStart)
            )
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(
                    subtitle,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5, widthDp = 360, heightDp = 220)
@Composable
fun HorizontalMenuPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Разделы",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    HorizontalMenuCard(
                        "Профиль", "Аккаунт",
                        rememberVectorPainter(Icons.Default.Person),
                        listOf(Color(0xFF6C63FF), Color(0xFF3F3D9E))
                    )
                }
                item {
                    HorizontalMenuCard(
                        "Заказы", "3 в пути",
                        rememberVectorPainter(Icons.Default.Menu),
                        listOf(Color(0xFFFF9800), Color(0xFFE65100))
                    )
                }
                item {
                    HorizontalMenuCard(
                        "Избранное", "12 товаров",
                        rememberVectorPainter(Icons.Default.Favorite),
                        listOf(Color(0xFFEC407A), Color(0xFFAD1457))
                    )
                }
            }
        }
    }
}