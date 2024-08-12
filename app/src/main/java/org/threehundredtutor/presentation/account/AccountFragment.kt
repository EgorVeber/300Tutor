package org.threehundredtutor.presentation.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.account.AccountComponent
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.presentation.favorites.SimpleItemTouchHelperCallback
import org.threehundredtutor.presentation.favorites.TouchManager
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.ActionDialogFragment
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_core.databinding.AccountFragmentBinding


class AccountFragment : BaseFragment(UiCoreLayout.account_fragment) {

    private val accountComponent by lazy {
        AccountComponent.createAccountComponent()
    }

    override val viewModel by viewModels<AccountViewModel> {
        accountComponent.viewModelMapFactory()
    }

    override val bottomMenuVisible: Boolean = false

    private lateinit var binding: AccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AccountFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.setFragmentResultListener(
            ActionDialogFragment.REQUEST_KEY,
            this
        ) { _, bundle ->
            when (bundle.getString(ActionDialogFragment.RESULT_KEY)) {
                ActionDialogFragment.POSITIVE_BUTTON_CLICKED -> viewModel.onLogoutClick()
            }
        }
    }

    private val delegateAdapter: TouchManager = TouchManager(
        onFinishedListener = { ->
            viewModel.finish()
        },
        onItemMoveListener = { from, to ->
            viewModel.fromTo(from, to)
        }
    )

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.recyclerSolution.adapter = delegateAdapter
        binding.logout.setOnClickListener { showLogoutDialog() }
        binding.mainToolBar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.telegramBotContainer.setOnClickListener { viewModel.onTelegramClicked() }
        binding.siteContainer.setOnClickListener { viewModel.onSiteClicked() }

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(delegateAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerSolution)
    }

    override fun onObserveData() {
        viewModel.getAccountInfoStateFlow().observeFlow(this) { account ->
            updateAccountInfo(account)
        }

        viewModel.getLoadingStateStateFlow().observeFlow(this) { loading ->
            loading(loading)
        }

        viewModel.getAccountUiEventState().observeFlow(this) { state ->
            when (state) {
                is AccountViewModel.AccountUiEvent.OpenSite -> openSite(state.urlAuthentication)

                is AccountViewModel.AccountUiEvent.ShowMessage -> showMessage(state.message)
                is AccountViewModel.AccountUiEvent.OpenTelegram -> openTelegram(
                    state.telegramBotUrl,
                    state.telegramBotName
                )

                AccountViewModel.AccountUiEvent.Logout -> logout()
            }
        }

        viewModel.getAccountErrorStateFlow().observeFlow(this) { errorAccount ->
            if (errorAccount) updateAccountError()
        }

        viewModel.getTelegramVisibleState().observeFlow(this) { result ->

        }
        viewModel.getUiItems().observeFlow(this) {
            delegateAdapter.items = it
        }
    }

    private fun openSite(urlAuthentication: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlAuthentication)))
        } catch (e: Exception) {
            showMessage(getString(UiCoreStrings.unknown_error_message))
        }
    }

    private fun showLogoutDialog() {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            title = getString(UiCoreStrings.confirm_logout),
            message = getString(UiCoreStrings.warning_logout),
        )
    }

    private fun openTelegram(telegramBotUrl: String, telegramBotName: String) {
        try {
            val uri = Uri.parse(TELEGRAM_LINK.replace(TELEGRAM_BOT_SUB_STRING, telegramBotName))
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(telegramBotUrl)))
            } catch (e: Exception) {
                showMessage(getString(UiCoreStrings.unknown_error_message))
            }
        }
    }

    private fun logout() {
        navigate(R.id.action_accountFragment_to_authorizationFragment)
    }

    private fun updateAccountInfo(accountInfo: AccountModel) {
        with(binding) {
            nameValueTextView.text = accountInfo.name
            surnameValueTextView.text = accountInfo.surname
            emailValueTextView.text = accountInfo.email
            phoneValueTextView.text = accountInfo.phoneNumber
        }
    }

    private fun updateAccountError() {
        with(binding) {
            nameValueTextView.text = getString(UiCoreStrings.profile_error_account)
            surnameValueTextView.text = getString(UiCoreStrings.profile_error_account)
            emailValueTextView.text = getString(UiCoreStrings.profile_error_account)
            phoneValueTextView.text = getString(UiCoreStrings.profile_error_account)
        }
    }

    private fun loading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    companion object {
        const val TELEGRAM_LINK = "tg://resolve?domain={botTelegram}"
        const val TELEGRAM_BOT_SUB_STRING = "{botTelegram}"
    }
}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onFinished()
}