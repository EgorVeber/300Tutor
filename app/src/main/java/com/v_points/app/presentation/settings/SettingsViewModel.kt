package com.v_points.app.presentation.settings

import com.v_points.app.domain.common.GetConfigUseCase
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

sealed interface SettingsNavigateAction {
    data object CreateGameDay : SettingsNavigateAction
}

class SettingsViewModel @Inject constructor(
    getConfigUseCase: GetConfigUseCase
) : BaseViewModel() {

    private val navigateAction = SingleSharedFlow<SettingsNavigateAction>()
    fun getNavigateAction(): Flow<SettingsNavigateAction> = navigateAction

    fun onGameDayClick() {
        navigateAction.tryEmit(SettingsNavigateAction.CreateGameDay)
    }

    fun onGameClick() {

    }

    init {

    }
}
