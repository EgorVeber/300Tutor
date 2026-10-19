package com.v_points.app.presentation.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import com.v_points.app.domain.common.GetConfigUseCase
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    getConfigUseCase: GetConfigUseCase
) : BaseViewModel() {
    private val chosenSchoolState = MutableStateFlow(false)

    init {
        chosenSchoolState.value = getConfigUseCase.invoke().changeDomain
    }

    fun getChosenSchoolState(): Flow<Boolean> = chosenSchoolState

}
