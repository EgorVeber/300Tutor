package org.threehundredtutor.presentation.restore

import androidx.fragment.app.viewModels
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel

class RestorePasswordFragment : BaseFragment(UiCoreLayout.restore_password_fragment){
    override val viewModel: BaseViewModel by viewModels()
}
