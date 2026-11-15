package com.v_points.app.presentation.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.UiCoreAttr
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.main_menu.MainMenuComponent
import com.v_points.app.presentation.course.WebViewFragment
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.applyWindowColor
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainFragmentBinding
import com.v_points.app.ui_core.setComposeContent

class MainMenuFragment : BaseFragment(UiCoreLayout.main_menu_fragment) {

    private val mainMenuComponent by lazy {
        MainMenuComponent.createMainMenuComponent()
    }

    override val viewModel by viewModels<MainMenuViewModel> {
        mainMenuComponent.viewModelMapFactory()
    }

    override var customHandlerBackStack = true

    override fun onBackPressed() {
        navigate(R.id.action_mainMenuFragment_to_mainFragment)
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
            MainMenuScreen(
                viewModel = viewModel,
                onAccountClick = {
                    navigate(R.id.action_mainMenuFragment_to_accountFragment)
                },
                onSettingsClick = {
                    navigate(R.id.action_mainMenuFragment_to_settingsFragment)
                }
            )
        }
    }

    override fun onObserveData() {
        viewModel.getUiAction().observeFlow(this) { action ->
            when (action) {
                is MainMenuViewModel.UiAction.NavigateWebScreen -> {
                    navigate(R.id.action_mainMenuFragment_to_webViewFragment, bundle = Bundle().apply {
                        putString(WebViewFragment.LINK_KEY, action.urlAuthentication)
                        putString(WebViewFragment.SCHOOL_KEY, action.schoolName)
                        putString(WebViewFragment.SITE_URL_KEY, action.siteUrl)
                    })
                }

                MainMenuViewModel.UiAction.NavigateFavoritesScreen -> {
                    navigate(R.id.action_mainMenuFragment_to_favoritesFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
