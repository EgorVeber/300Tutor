package com.v_points.app.vpoint.game_day_create.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.vpoint.fiersote.domain.GymModel

interface CreateGameDayState {
    val selectedDateText: String?
    val selectedGymName: String?
    val isCreateEnabled: Boolean
    val isDateDialogVisible: Boolean
    val isGymDialogVisible: Boolean
    val gyms: List<GymModel>
}

class MutableCreateGameDayState : CreateGameDayState {
    override var selectedDateText: String? by mutableStateOf(null)
    override var selectedGymName: String? by mutableStateOf(null)
    override var isCreateEnabled: Boolean by mutableStateOf(false)
    override var isDateDialogVisible: Boolean by mutableStateOf(false)
    override var isGymDialogVisible: Boolean by mutableStateOf(false)
    override var gyms: List<GymModel> by mutableStateOf(listOf())

    var selectedTimestamp: Long? = null
    var selectedGymId: String? = null
}
