package com.v_points.app.vpoint.main

import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel

sealed interface MainScreenAction {
    data class OnMenuItemCLick(val item: MainMenuUiModel): MainScreenAction
}

sealed interface MainScreenNavigateAction {
    data object Game : MainScreenNavigateAction
    data object Profile : MainScreenNavigateAction
    data object Users : MainScreenNavigateAction
}