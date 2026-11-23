package com.v_points.app.ui_core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun FullScreenLoader(
    isLoading: Boolean,
    message: String = "Загрузка..."
) {
    if (!isLoading) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f) // Гарантирует отрисовку поверх других элементов
            .background(Color.Black.copy(alpha = 0.5f)) // Полупрозрачный фон
            .pointerInput(Unit) {
                // Блокирует клики, проходящие сквозь оверлей
                awaitEachGesture {
                    awaitFirstDown(requireUnconsumed = false)
                    // События потребляются, ничего не передается ниже
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(color = Color.White)
            Text(
                text = message,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}