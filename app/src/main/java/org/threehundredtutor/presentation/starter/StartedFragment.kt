package org.threehundredtutor.presentation.starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.databinding.StartedFragmentBinding
import org.threehundredtutor.di.starter.StartedComponent

class StartedFragment : BaseFragment(R.layout.started_fragment) {

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
}
