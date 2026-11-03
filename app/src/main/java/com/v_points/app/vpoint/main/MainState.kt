package com.v_points.app.vpoint.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel

interface MainState {
    val items: List<GameDayUiModel>
}

class MutableMainState : MainState {
    override var items: List<GameDayUiModel> by mutableStateOf(listOf())
}