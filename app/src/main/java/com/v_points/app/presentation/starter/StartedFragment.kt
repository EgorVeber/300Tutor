package com.v_points.app.presentation.starter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.UiCoreAttr
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.starter.StartedComponent
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.applyWindowColor
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.dropWindowColor
import com.v_points.app.ui_core.databinding.StartedFragmentBinding

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
