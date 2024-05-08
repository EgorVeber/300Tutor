package org.threehundredtutor.presentation.main_menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.main_menu.MainMenuComponent
import org.threehundredtutor.presentation.main_menu.adapter.MainMenuManager
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_core.databinding.MainMenuFragmentBinding

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
    }

    override fun onObserveData() {
        viewModel.getAccountStateFlow().observeFlow(this) { accountModel ->
            binding.accountName.text = accountModel.name + accountModel.surname
            binding.accountSubtitle.text = accountModel.email
        }
        viewModel.getUiItemStateFlow().observeFlow(this) { subjectUiItems ->
            delegateAdapter.items = subjectUiItems
        }
    }
}