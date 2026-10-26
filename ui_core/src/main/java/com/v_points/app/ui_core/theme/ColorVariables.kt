package com.v_points.app.ui_core.theme

import androidx.compose.material3.lightColorScheme

internal object ColorVariables {

    // Text color
    val TextPrimary = ColorPrimitives.Gray950
    val TextSecondary = ColorPrimitives.Gray400
    val TextPrimaryOnBrand = ColorPrimitives.Gray0
    val TextSecondaryOnBrand = ColorPrimitives.AlphaWhite70
    val TextDisabled = ColorPrimitives.Gray300
    val TextAccent = ColorPrimitives.Gray950
    val TextPositive = ColorPrimitives.Positive600
    val TextWarning = ColorPrimitives.Warning600
    val TextNegative = ColorPrimitives.Negative600

    // Background / Base
    val BgPrimary = ColorPrimitives.GraySolid50
    val BgSecondary = ColorPrimitives.Gray0
    val BgTertiary = ColorPrimitives.Gray25
    val BgOverlayPrimary = ColorPrimitives.Gray200
    val BgOverlaySecondary = ColorPrimitives.AlphaBlack40
    val BgOverlayDisabled = ColorPrimitives.AlphaWhite60
    val BgDisabled = ColorPrimitives.AlphaWhite40

    // Background / Alerts
    val BgPositivePrimary = ColorPrimitives.Positive500
    val BgPositiveSecondary = ColorPrimitives.Positive200
    val BgPositiveTertiary = ColorPrimitives.Positive50
    val BgWarningPrimary = ColorPrimitives.Warning500
    val BgWarningSecondary = ColorPrimitives.Warning200
    val BgWarningTertiary = ColorPrimitives.Warning50
    val BgNegativePrimary = ColorPrimitives.Negative500
    val BgNegativeSecondary = ColorPrimitives.Negative200
    val BgNegativeTertiary = ColorPrimitives.Negative50
    val BgSystemPrimary = ColorPrimitives.System700
    val BgSystemSecondary = ColorPrimitives.System200

    // Background / Brand
    val BgAllPrimary = ColorPrimitives.BrandAllInOne950
    val BgGymPrimary = ColorPrimitives.MintGreen300
    val BgCyberPrimary = ColorPrimitives.Blue500
    val BgBikePrimary = ColorPrimitives.LightGreen500
    val BgBilliardPrimary = ColorPrimitives.GreenVelvet500
    val BgUrbanPrimary = ColorPrimitives.BrickOrange500
    val BgIteraPrimary = ColorPrimitives.Burgundy500

    // Background / Accent
    val BgAccentPrimary = ColorPrimitives.Gray950
    val BgAccentSecondary = ColorPrimitives.Gray200
    val BgAccentTertiary = ColorPrimitives.Gray0
    val BgAccentDisabled = ColorPrimitives.Gray100
    val BgAccentCyber = ColorPrimitives.Blue200
    val BgAccentBilliard = ColorPrimitives.GreenVelvet200

    // Border
    val BorderPrimary = ColorPrimitives.Gray100
    val BorderSecondary = ColorPrimitives.Gray50
    val BorderPrimaryDisabled = ColorPrimitives.Gray100
    val BorderAccentPrimary = ColorPrimitives.BrandAllInOne950
    val BorderAccentSecondary = ColorPrimitives.BrandAllInOne200
    val BorderSeparator = ColorPrimitives.Gray25
    val BorderOnSurface = ColorPrimitives.AlphaWhite100
    val BorderPositive = ColorPrimitives.Positive500
    val BorderWarning = ColorPrimitives.Warning500
    val BorderError = ColorPrimitives.Negative500
    val BorderGym = ColorPrimitives.MintGreen300
    val BorderCyber = ColorPrimitives.Blue500

    // Foreground
    val FgPrimary = ColorPrimitives.Gray900
    val FgSecondary = ColorPrimitives.Gray400
    val FgTertiary = ColorPrimitives.AlphaWhite70
    val FgDisabled = ColorPrimitives.Gray300
    val FgAccent = ColorPrimitives.Gray900
    val FgOnBrand = ColorPrimitives.Gray0
    val FgAccentCyber = ColorPrimitives.Blue500
    val FgPositive = ColorPrimitives.Positive500
    val FgNegative = ColorPrimitives.Negative500
    val FgWarning = ColorPrimitives.Warning500
    val FgSystem = ColorPrimitives.System700

    // region Components

    // Components / Button / Primary
    val ButtonPrimaryBg = ColorPrimitives.BrandAllInOne950
    val ButtonPrimaryBgPressed = ColorPrimitives.BrandAllInOne900
    val ButtonPrimaryBgDisabled = ColorPrimitives.BrandAllInOne200
    val ButtonPrimaryFg = ColorPrimitives.Gray0

    // Components / Button / Secondary
    val ButtonSecondaryBg = ColorPrimitives.BrandAllInOne50
    val ButtonSecondaryBgPressed = ColorPrimitives.BrandAllInOne100
    val ButtonSecondaryBgDisabled = ColorPrimitives.BrandAllInOne25
    val ButtonSecondaryFg = ColorPrimitives.BrandAllInOne950
    val ButtonSecondaryFgDisabled = ColorPrimitives.BrandAllInOne300

    // Components / Button / Tertiary
    val ButtonTertiaryBg = ColorPrimitives.Gray0
    val ButtonTertiaryBgPressed = ColorPrimitives.Gray50
    val ButtonTertiaryBgDisabled = ColorPrimitives.Gray0
    val ButtonTertiaryFg = ColorPrimitives.BrandAllInOne950
    val ButtonTertiaryFgDisabled = ColorPrimitives.BrandAllInOne300

    // Components / Button / Surface
    val ButtonOnSurfaceBg = ColorPrimitives.AlphaWhite10
    val ButtonOnSurfaceBgPressed = ColorPrimitives.AlphaWhite20
    val ButtonOnSurfaceBgDisabled = ColorPrimitives.AlphaWhite10
    val ButtonOnSurfaceFg = ColorPrimitives.Gray0
    val ButtonOnSurfaceFgDisabled = ColorPrimitives.AlphaWhite30

    // Components / Button / Negative
    val ButtonNegativePrimaryBg = ColorPrimitives.Negative50
    val ButtonNegativePrimaryBgPressed = ColorPrimitives.Negative100
    val ButtonNegativePrimaryBgDisabled = ColorPrimitives.Negative25
    val ButtonNegativePrimaryFg = ColorPrimitives.Negative500
    val ButtonNegativePrimaryFgDisabled = ColorPrimitives.Negative100
    val ButtonNegativeSecondaryBg = ColorPrimitives.BrandAllInOne25
    val ButtonNegativeSecondaryBgPressed = ColorPrimitives.BrandAllInOne50
    val ButtonNegativeSecondaryBgDisabled = ColorPrimitives.BrandAllInOne25
    val ButtonNegativeSecondaryFg = ColorPrimitives.Negative500
    val ButtonNegativeSecondaryFgDisabled = ColorPrimitives.Negative100
    val ButtonNegativeTertiaryBg = ColorPrimitives.Gray0
    val ButtonNegativeTertiaryBgPressed = ColorPrimitives.Gray50
    val ButtonNegativeTertiaryBgDisabled = ColorPrimitives.Gray0
    val ButtonNegativeTertiaryFg = ColorPrimitives.Negative500
    val ButtonNegativeTertiaryFgDisabled = ColorPrimitives.Negative100

    // Components / Handle
    val HandleBg = ColorPrimitives.Gray100

    // Components / Skeleton
    val SkeletonBg = ColorPrimitives.Gray50

    // Components / Snackbar
    val SnackbarBg = ColorPrimitives.Gray950

    // Components / Graphic
    val GraphicContrastBorder = ColorPrimitives.Gray25

    // Components / Tabbar
    val TabbarBg = ColorPrimitives.Gray0
    val TabbarTxtActive = ColorPrimitives.Gray950
    val TabbarFgActiveTab = ColorPrimitives.Gray950
    val TabbarFgInactiveTab = ColorPrimitives.Gray400
    val TabbarTextInactiveTab = ColorPrimitives.Gray400

    // Components / Pagination
    val PaginationItemBgLightActive = ColorPrimitives.AlphaBlack100
    val PaginationItemBgLightInactive = ColorPrimitives.AlphaBlack20
    val PaginationItemBgDarkActive = ColorPrimitives.AlphaWhite100
    val PaginationItemBgDarkInactive = ColorPrimitives.AlphaWhite40
}