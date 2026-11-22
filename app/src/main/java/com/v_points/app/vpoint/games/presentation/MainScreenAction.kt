package com.v_points.app.vpoint.games.presentation

sealed interface GameScreenAction {
}

sealed interface GameScreenNavigateAction {
    data object OnBackClick : GameScreenNavigateAction
    data object OnCreateGameClick : GameScreenNavigateAction
}