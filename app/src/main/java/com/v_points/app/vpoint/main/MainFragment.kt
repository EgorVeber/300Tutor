package com.v_points.app.vpoint.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.main.MainComponent
import com.v_points.app.presentation.course.WebViewFragment
import com.v_points.app.presentation.main_menu.MainMenuViewModel
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainFragmentBinding
import com.v_points.app.ui_core.setComposeContent

class MainFragment : BaseFragment(UiCoreLayout.main_fragment) {

    private val mainComponent by lazy {
        MainComponent.createHomeComponent()
    }

    override var customHandlerBackStackWithDelay = true

    override val viewModel: MainViewModel by viewModels {
        mainComponent.viewModelMapFactory()
    }

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setComposeContent {
            MainScreenRoute(viewModel = viewModel)
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                MainScreenNavigateAction.Game -> {

                }
                MainScreenNavigateAction.Profile -> {

                }
                MainScreenNavigateAction.Users -> {
                    navigate(R.id.action_mainFragment_to_participantsFScreen)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
