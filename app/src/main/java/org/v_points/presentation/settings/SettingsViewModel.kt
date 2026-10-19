package org.v_points.presentation.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.v_points.domain.common.GetConfigUseCase
import org.v_points.ui_common.fragment.base.BaseViewModel
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
