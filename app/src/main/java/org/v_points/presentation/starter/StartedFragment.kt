package org.v_points.presentation.starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.v_points.R
import org.v_points.core.UiCoreAttr
import org.v_points.core.UiCoreLayout
import org.v_points.core.navigate
import org.v_points.di.starter.StartedComponent
import org.v_points.ui_common.flow.observeFlow
import org.v_points.ui_common.fragment.applyWindowColor
import org.v_points.ui_common.fragment.base.BaseFragment
import org.v_points.ui_common.fragment.dropWindowColor
import org.v_points.ui_core.databinding.StartedFragmentBinding

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
        super.onViewCreated(view, savedInstanceState)
        applyWindowColor(UiCoreAttr.defaultBlack)
    }

    override fun onObserveData() {
        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                StarterViewModel.UiEvent.NavigateAuthorizationScreen -> {
                    navigate(R.id.action_startedFragment_to_homeFragment)
                }

                StarterViewModel.UiEvent.NavigateHomeScreen -> {
                    navigate(R.id.action_startedFragment_to_homeFragment)
                }

                StarterViewModel.UiEvent.NavigateRegistrationScreen -> {
                    navigate(R.id.action_startedFragment_to_homeFragment)
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
