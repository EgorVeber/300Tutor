package org.threehundredtutor.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.settings.SettingsComponent
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_core.databinding.SettingFragmentBinding

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