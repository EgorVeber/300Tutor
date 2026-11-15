package com.v_points.app.vpoint.game_day_create.presentation

import com.v_points.app.vpoint.fiersote.domain.GymModel

sealed interface CreateGameDayAction {
    data object OnDateClick : CreateGameDayAction
    data class OnDateSelected(val timestamp: Long?) : CreateGameDayAction
    data object OnGymClick : CreateGameDayAction
    data class OnGymSelected(val gym: GymModel?) : CreateGameDayAction
    data object OnDismissDialogs : CreateGameDayAction
    data object OnCreateClick : CreateGameDayAction
}
