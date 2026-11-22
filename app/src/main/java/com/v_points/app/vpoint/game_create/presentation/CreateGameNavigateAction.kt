package com.v_points.app.vpoint.game_create.presentation

import com.v_points.app.ui_core.SnackBarType

sealed interface CreateGameNavigateAction {
    data class ShowSnack(val message: String, val snackBarType: SnackBarType) :
        CreateGameNavigateAction

    data object SuccessCreated : CreateGameNavigateAction
}