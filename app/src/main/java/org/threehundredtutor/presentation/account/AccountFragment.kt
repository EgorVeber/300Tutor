package org.threehundredtutor.presentation.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.SITE_URL
import org.threehundredtutor.common.TELEGRAM_BOT_URL
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.AccountFragmentBinding
import org.threehundredtutor.di.account.AccountComponent
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.presentation.common.ActionDialogFragment

class AccountFragment : BaseFragment(R.layout.account_fragment) {

    private val accountComponent by lazy {
        AccountComponent.createAccountComponent()
    }

    override val viewModel by viewModels<AccountViewModel> {
        accountComponent.viewModelMapFactory()
    }

    private lateinit var binding: AccountFragmentBinding

    override var customHandlerBackStack = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AccountFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        navigate(R.id.action_accountFragment_to_homeFragment)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.logout.setOnClickListener { showLogoutDialog() }
        binding.telegramBotContainer.setOnClickListener { showTelegramDialog() }
        binding.siteContainer.setOnClickListener { showSiteDialog() }
    }

    override fun onObserveData() {
        viewModel.getAccountInfoStateFlow().observeFlow(this) { account ->
            updateAccountInfo(account)
        }

        viewModel.getLoadingStateStateFlow().observeFlow(this) { loading ->
            loading(loading)
        }

        viewModel.getaccountUiEventState().observeFlow(this) { state ->
            when (state) {
                is AccountViewModel.AccountUiEvent.OpenSite -> openSite(
                    state.urlAuthentication,
                )

                is AccountViewModel.AccountUiEvent.ShowMessage -> showMessage(state.message)
                AccountViewModel.AccountUiEvent.Logout -> logout()
                AccountViewModel.AccountUiEvent.OpenTelegram -> openTelegram()
            }
        }

        viewModel.getAccountErrorStateFlow().observeFlow(this) { errorAccount ->
            if (errorAccount) updateAccountError()
        }
    }

    private fun openSite(urlAuthentication: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlAuthentication)))
        } catch (e: Exception) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL)))
        }
    }

    private fun showLogoutDialog() {
        ActionDialogFragment.showDialog(
            title = getString(R.string.confirm_logout),
            fragmentManager = childFragmentManager,
            message = getString(R.string.warning_logout),
            onPositiveClick = { viewModel.onLogoutClick() }
        )
    }

    private fun showTelegramDialog() {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            message = getString(R.string.open_telegram),
            onPositiveClick = { viewModel.onTelegramClicked() }
        )
    }

    private fun showSiteDialog() {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            message = getString(R.string.open_site),
            onPositiveClick = { viewModel.onSiteClicked() }
        )
    }

    private fun openTelegram() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM_BOT_URL)))
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
            nameValueTextView.text = getString(R.string.profile_error_account)
            surnameValueTextView.text = getString(R.string.profile_error_account)
            emailValueTextView.text = getString(R.string.profile_error_account)
            phoneValueTextView.text = getString(R.string.profile_error_account)
        }
    }

    private fun loading(loading: Boolean) {
        binding.progress.isVisible = loading
    }
}

