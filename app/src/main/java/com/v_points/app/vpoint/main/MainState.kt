package com.v_points.app.vpoint.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel

interface MainState {
    val items: List<MainMenuUiModel>
}

class MutableMainState : MainState {
    override var items: List<MainMenuUiModel> by mutableStateOf(listOf())
}