package com.v_points.app.presentation.restore

import androidx.fragment.app.viewModels
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.base.BaseViewModel

class RestorePasswordFragment : BaseFragment(UiCoreLayout.restore_password_fragment) {
    override val viewModel: BaseViewModel by viewModels()
}
