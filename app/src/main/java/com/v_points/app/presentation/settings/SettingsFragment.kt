package com.v_points.app.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.settings.SettingsComponent
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.setComposeContent

class SettingsFragment : BaseFragment(UiCoreLayout.setting_fragment) {

    override val bottomMenuVisible: Boolean = false

    private val settingsComponent by lazy {
        SettingsComponent.createSettingsComponent()
    }

    override val viewModel: SettingsViewModel by viewModels {
        settingsComponent.viewModelMapFactory()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as androidx.compose.ui.platform.ComposeView).setComposeContent(insets = null) {
            SettingsScreen(
                viewModel = viewModel,
                onBackClick = { findNavController().popBackStack() },
            )
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                SettingsNavigateAction.CreateGameDay -> {
                    navigate(R.id.action_settingsFragment_to_createGameDayFragment)
                }
            }
        }
    }
}
