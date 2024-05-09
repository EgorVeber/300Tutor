package org.threehundredtutor.presentation.starter

import org.threehundredtutor.domain.starter.GetThemePrefsUseCase
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val getThemePrefsUseCase: GetThemePrefsUseCase,
) : BaseViewModel() {

    fun onCreateActivity(): Int = getThemePrefsUseCase()
}