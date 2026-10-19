package org.threehundredtutor.ui_core.theme// ui/theme/Theme.kt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val TutorColorScheme = lightColorScheme(
    primary = PrimaryTutor,
    onPrimary = ContentBackgroundTutor,
    primaryContainer = ContentBackgroundTutor,
    onPrimaryContainer = TextPrimaryTutor,

    secondary = SecondaryTutor,
    onSecondary = DefaultWhite,

    background = BackgroundTutor,
    onBackground = TextPrimaryTutor,

    surface = BackgroundTutor,
    onSurface = SecondaryTutor,
    surfaceVariant = DefaultTransparent,
    onSurfaceVariant = TextPrimaryTutor,

    error = WarningTutor,
    onError = WarningBackgroundTutor,

    outline = Separator60Tutor,
    outlineVariant = Secondary40Tutor
)

private val TutorExtendedColors = ExtendedColors(
    primary40 = PrimaryTutor40,
    secondary40 = Secondary40Tutor,
    secondary60 = Secondary60Tutor,
    separator = SeparatorTutor,
    separator60 = Separator60Tutor,
    ripple = RippleTutor,
    ripple15 = Ripple15Tutor,
    customText = CustomTextTutor,
    needCheckQuestion = NeedCheckQuestion,
    contentBackground = ContentBackgroundTutor,

    defaultWhite = DefaultWhite,
    defaultWhite80 = DefaultWhite80,
    defaultWhite60 = DefaultWhite60,
    defaultWhite50 = DefaultWhite50,
    defaultWhite40 = DefaultWhite40,
    defaultWhite20 = DefaultWhite20,
    defaultWhite10 = DefaultWhite10,
    defaultBlack = DefaultBlack,
    defaultBlack60 = DefaultBlack60,
    defaultBlack40 = DefaultBlack40,
    defaultBlack20 = DefaultBlack20,
    defaultBlack10 = DefaultBlack10,
    defaultGray = DefaultGray,
    defaultRed = DefaultRed,
    defaultRed20 = DefaultRed20,
    defaultRed10 = DefaultRed10,
    defaultGreen = DefaultGreen,
    defaultGreen20 = DefaultGreen20,
    defaultGreen10 = DefaultGreen10,
    defaultLightGreen = DefaultLightGreen,
    defaultViolet = DefaultViolet,
    defaultPurple = DefaultPurple,
    defaultBlue = DefaultBlue,
    defaultTeal = DefaultTeal,
    defaultYellow = DefaultYellow,
    defaultYellow20 = DefaultYellow20,
    defaultLightYellow = DefaultLightYellow,
    defaultOrange = DefaultOrange,
    defaultDarkOrange = DefaultDarkOrange,
    defaultDarkOrange20 = DefaultDarkOrange20,
    defaultDarkOrange10 = DefaultDarkOrange10,
    defaultBrown = DefaultBrown,
    defaultPink = DefaultPink,
    defaultDarkPink = DefaultDarkPink,
    defaultBackground = DefaultBackground,
    defaultTransparent = DefaultTransparent
)

@Composable
fun TutorTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalExtendedColors provides TutorExtendedColors) {
        MaterialTheme(
            colorScheme = TutorColorScheme,
            content = content
        )
    }
}