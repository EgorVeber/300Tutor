package com.v_points.app.vpoint.game_day

import com.v_points.app.ui_core.components.game_day.GameDayUiModel

sealed interface MainScreenAction {
    data class OnGameDayClick(val item: GameDayUiModel): MainScreenAction
}

sealed interface MainScreenNavigateAction {
    data object Game : MainScreenNavigateAction
    data object Profile : MainScreenNavigateAction
    data object Users : MainScreenNavigateAction
}