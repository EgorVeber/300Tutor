package com.v_points.app.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.settings.SettingsComponent
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.SettingFragmentBinding

class SettingsFragment : BaseFragment(UiCoreLayout.setting_fragment) {

    override val bottomMenuVisible: Boolean = false

    private lateinit var binding: SettingFragmentBinding

    private val settingsComponent by lazy {
        SettingsComponent.createSettingsComponent()
    }

    override val viewModel by viewModels<SettingsViewModel> {
        settingsComponent.viewModelMapFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SettingFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.rootSchool.setOnClickListener {
            navigate(R.id.action_settingsFragment_to_schoolFragment)
        }
        binding.settingsToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun onObserveData() {
        viewModel.getChosenSchoolState().observeFlow(this) { visible ->
            binding.rootSchool.isVisible = visible
        }
    }
}