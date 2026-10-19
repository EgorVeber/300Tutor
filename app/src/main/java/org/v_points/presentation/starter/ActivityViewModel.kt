package org.v_points.presentation.starter

import org.v_points.domain.starter.GetThemePrefsUseCase
import org.v_points.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val getThemePrefsUseCase: GetThemePrefsUseCase,
) : BaseViewModel() {

    fun onCreateActivity(): Int = getThemePrefsUseCase()
}