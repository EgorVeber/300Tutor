package com.v_points.app.ui_core.components.menu_item

import androidx.annotation.DrawableRes


sealed interface MainMenuUiModel {
    val title: String
    val subtitle: String?

    @get:DrawableRes
    val iconRes: Int?

    data class Users(
        override val title: String,
        override val subtitle: String?,
        override val iconRes: Int?
    ) : MainMenuUiModel

    data class Games(
        override val title: String,
        override val subtitle: String?,
        override val iconRes: Int?
    ) : MainMenuUiModel

    data class Profile(
        override val title: String,
        override val subtitle: String?,
        override val iconRes: Int?
    ) : MainMenuUiModel
}