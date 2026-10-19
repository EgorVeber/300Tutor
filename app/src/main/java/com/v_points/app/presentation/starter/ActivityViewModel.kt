package com.v_points.app.presentation.starter

import com.v_points.app.domain.starter.GetThemePrefsUseCase
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val getThemePrefsUseCase: GetThemePrefsUseCase,
) : BaseViewModel() {

    fun onCreateActivity(): Int = getThemePrefsUseCase()
}