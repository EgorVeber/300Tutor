package org.v_points.presentation.restore

import androidx.fragment.app.viewModels
import org.v_points.core.UiCoreLayout
import org.v_points.ui_common.fragment.base.BaseFragment
import org.v_points.ui_common.fragment.base.BaseViewModel

class RestorePasswordFragment : BaseFragment(UiCoreLayout.restore_password_fragment) {
    override val viewModel: BaseViewModel by viewModels()
}
