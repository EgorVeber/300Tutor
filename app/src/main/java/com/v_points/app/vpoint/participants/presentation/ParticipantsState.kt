package com.v_points.app.vpoint.participants.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel
import com.v_points.app.ui_core.components.users_variants.UserUiModel

interface ParticipantsState {
    val users: List<UserUiModel>
}

class MutableParticipantsState : ParticipantsState {
    override var users: List<UserUiModel> by mutableStateOf(listOf())
}