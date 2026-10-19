package com.v_points.app.ui_core.theme// ui/theme/ExtendedColors.kt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    // Tutor extras
    val primary40: Color,
    val secondary40: Color,
    val secondary60: Color,
    val separator: Color,
    val separator60: Color,
    val ripple: Color,
    val ripple15: Color,
    val customText: Color,
    val needCheckQuestion: Color,
    val contentBackground: Color,

    // Default palette
    val defaultWhite: Color,
    val defaultWhite80: Color,
    val defaultWhite60: Color,
    val defaultWhite50: Color,
    val defaultWhite40: Color,
    val defaultWhite20: Color,
    val defaultWhite10: Color,
    val defaultBlack: Color,
    val defaultBlack60: Color,
    val defaultBlack40: Color,
    val defaultBlack20: Color,
    val defaultBlack10: Color,
    val defaultGray: Color,
    val defaultRed: Color,
    val defaultRed20: Color,
    val defaultRed10: Color,
    val defaultGreen: Color,
    val defaultGreen20: Color,
    val defaultGreen10: Color,
    val defaultLightGreen: Color,
    val defaultViolet: Color,
    val defaultPurple: Color,
    val defaultBlue: Color,
    val defaultTeal: Color,
    val defaultYellow: Color,
    val defaultYellow20: Color,
    val defaultLightYellow: Color,
    val defaultOrange: Color,
    val defaultDarkOrange: Color,
    val defaultDarkOrange20: Color,
    val defaultDarkOrange10: Color,
    val defaultBrown: Color,
    val defaultPink: Color,
    val defaultDarkPink: Color,
    val defaultBackground: Color,
    val defaultTransparent: Color
)

val LocalExtendedColors = staticCompositionLocalOf<ExtendedColors> {
    error("ExtendedColors not provided. Wrap content in TutorTheme.")
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current