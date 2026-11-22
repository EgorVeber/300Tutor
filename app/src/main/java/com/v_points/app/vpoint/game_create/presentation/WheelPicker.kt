package com.v_points.app.vpoint.game_create.presentation

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberWheel(
    label: String,
    modifier: Modifier = Modifier,
    initialValue: Int = 0,
    count: Int = 50,
    itemHeight: Dp = 52.dp, // Увеличили высоту строки под крупный шрифт
    onValueChange: (Int) -> Unit
) {
    val safeInitial = initialValue.coerceIn(0, count - 1)
    val state = rememberLazyListState(initialFirstVisibleItemIndex = safeInitial)

    val current by remember {
        derivedStateOf { state.firstVisibleItemIndex.coerceIn(0, count - 1) }
    }

    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) onValueChange(current)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Крупная и аккуратная подпись сверху
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(12.dp))
        LazyColumn(
            state = state,
            flingBehavior = rememberSnapFlingBehavior(state),
            contentPadding = PaddingValues(vertical = itemHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(itemHeight * 3)
                .width(84.dp) // Чуть шире для крупных цифр
        ) {
            items(count) { index ->
                val isSelected = index == current
                Box(Modifier.height(itemHeight), contentAlignment = Alignment.Center) {
                    Text(
                        text = index.toString().padStart(2, '0'),
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontSize = if (isSelected) 34.sp else 24.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontFeatureSettings = "tnum" // Моноширинные цифры без «прыжков»
                        ),
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        modifier = Modifier.alpha(if (isSelected) 1f else 0.35f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DualWheelPreview() {
    MaterialTheme {
        Surface(Modifier.padding(24.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberWheel(
                    label = "Минуты",
                    initialValue = 15, // Старт с 15
                    onValueChange = {}
                )
                NumberWheel(
                    label = "Секунды",
                    initialValue = 30, // Старт с 30
                    onValueChange = {}
                )
            }
        }
    }
}
