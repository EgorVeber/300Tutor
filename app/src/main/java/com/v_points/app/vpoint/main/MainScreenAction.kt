package com.v_points.app.vpoint.main

import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel

sealed interface MainScreenAction {
    data class OnMenuItemCLick(val item: MainMenuUiModel): MainScreenAction
}