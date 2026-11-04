package com.v_points.app.vpoint.game_day

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.game_day.GameDayUiModel

interface MainState {
    val items: List<GameDayUiModel>
}

class MutableMainState : MainState {
    override var items: List<GameDayUiModel> by mutableStateOf(listOf())
}