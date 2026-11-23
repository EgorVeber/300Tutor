package com.v_points.app.vpoint.games.presentation

sealed interface GameScreenAction {
    data object OnCreateGameClick : GameScreenAction
    data object OnBackClick : GameScreenAction
}

sealed interface GameScreenNavigateAction {
    data object OnBackClick : GameScreenNavigateAction
    data class OnCreateGameClick(val one: List<String>, val two: List<String>) :
        GameScreenNavigateAction
}