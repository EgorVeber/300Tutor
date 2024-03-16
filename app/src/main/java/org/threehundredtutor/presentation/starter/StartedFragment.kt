package org.threehundredtutor.presentation.starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.starter.StartedComponent
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.applyWindowColor
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.dropWindowColor
import org.threehundredtutor.ui_core.databinding.StartedFragmentBinding

class StartedFragment : BaseFragment(UiCoreLayout.started_fragment) {

    private lateinit var binding: StartedFragmentBinding
    override val bottomMenuVisible = false

    private val startedComponent by lazy {
        StartedComponent.createStartedComponent()
    }

    override val viewModel by viewModels<StarterViewModel> {
        startedComponent.viewModelMapFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = StartedFragmentBinding.bind(view)
        applyWindowColor(UiCoreAttr.defaultBlack)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onObserveData() {
        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                StarterViewModel.UiEvent.NavigateAuthorizationScreen -> {
                    navigate(R.id.action_startedFragment_to_authorizationFragment)
                }

                StarterViewModel.UiEvent.NavigateHomeScreen -> {
                    navigate(R.id.action_startedFragment_to_homeFragment)
                }

                StarterViewModel.UiEvent.NavigateRegistrationScreen -> {
                    navigate(R.id.action_startedFragment_to_registrationFragment)
                }

                StarterViewModel.UiEvent.Empty -> {}
            }
        }
    }

    override fun onDestroyView() {
        dropWindowColor()
        super.onDestroyView()
    }
}
