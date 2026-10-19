package com.v_points.app.presentation.main_menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.main_menu.MainMenuComponent
import com.v_points.app.presentation.course.WebViewFragment
import com.v_points.app.presentation.main_menu.adapter.MainMenuManager
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainMenuFragmentBinding

class MainMenuFragment : BaseFragment(UiCoreLayout.main_menu_fragment) {

    private val mainMenuComponent by lazy {
        MainMenuComponent.createMainMenuComponent()
    }

    override val viewModel by viewModels<MainMenuViewModel> {
        mainMenuComponent.viewModelMapFactory()
    }

    private lateinit var binding: MainMenuFragmentBinding

    override var customHandlerBackStack = true

    private val delegateAdapter: MainMenuManager = MainMenuManager(
        mainMenuUiItemClickListener = { mainMenuItem ->
            viewModel.onMenuItemClicked(mainMenuItem)
        },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = MainMenuFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        navigate(R.id.action_mainMenuFragment_to_mainFragment)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.mainRecycler.adapter = delegateAdapter
        binding.accountInfo.setOnClickListener {
            navigate(R.id.action_mainMenuFragment_to_accountFragment)
        }
        binding.settingsImageView.setOnClickListener {
            navigate(R.id.action_mainMenuFragment_to_settingsFragment)
        }
    }

    override fun onObserveData() {
        viewModel.getAccountStateFlow().observeFlow(this) { accountModel ->
            binding.accountName.text = accountModel.name + " " + accountModel.surname
            binding.accountSubtitle.text = accountModel.email
        }
        viewModel.getUiItemStateFlow().observeFlow(this) { subjectUiItems ->
            delegateAdapter.items = subjectUiItems
        }
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
}