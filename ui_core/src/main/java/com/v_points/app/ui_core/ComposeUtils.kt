package com.v_points.app.ui_core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

fun ComposeView.setComposeContent(
    insets: @Composable (() -> WindowInsets)? = { WindowInsets.statusBars },
    contentScreen: @Composable () -> Unit,
) {
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    setContent {
        MaterialTheme {
            insets?.let { safeInsets ->
                Box(modifier = Modifier.windowInsetsPadding(insets = safeInsets.invoke())) { contentScreen.invoke() }
            } ?: contentScreen.invoke()
        }
    }
}

@Composable
fun Dp.dpToPx(): Float = LocalDensity.current.run { toPx() }

@Composable
fun Dp.dpToSp(): TextUnit = LocalDensity.current.run { toSp() }

@Composable
fun Int.pxToDp(): Dp = LocalDensity.current.run { toDp() }

@Composable
fun Float.pxToDp(): Dp = LocalDensity.current.run { toDp() }