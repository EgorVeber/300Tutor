package org.threehundredtutor.presentation.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
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
