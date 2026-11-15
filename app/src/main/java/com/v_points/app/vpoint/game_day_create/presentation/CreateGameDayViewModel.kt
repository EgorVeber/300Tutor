package com.v_points.app.vpoint.game_day_create.presentation

import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class CreateGameDayViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val state = MutableCreateGameDayState()

    fun getState(): CreateGameDayState = state
}
