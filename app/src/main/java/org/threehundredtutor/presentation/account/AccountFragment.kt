package org.threehundredtutor.presentation.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.TELEGRAM_KURSBIO_BOT_URL
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.utils.PrefsSettings
import org.threehundredtutor.databinding.AccountFragmentBinding
import org.threehundredtutor.di.account.AccountComponent
import org.threehundredtutor.domain.account.AccountModel

class AccountFragment : BaseFragment(R.layout.account_fragment) {

    private val accountComponent by lazy {
        AccountComponent.createAccountComponent()
    }

    override val viewModel by viewModels<AccountViewModel> {
        accountComponent.viewModelMapFactory()
    }

    private lateinit var binding: AccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AccountFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
        with(binding) {
            logout.setOnClickListener { viewModel.onLogoutClick() }
            telegramBotContainer.setOnClickListener { viewModel.onTelegramClicked() }
        }
    }

    override fun onObserveData() {
        viewModel.getAccountStateFlow().observeFlow(this) { account ->
            updateAccountInfo(account)
        }

        viewModel.getLoadingStateStateFlow().observeFlow(this) { loading ->
            loading(loading)
        }

        viewModel.getActionAccountSharedFlow().observeFlow(this) { state ->
            when (state) {
                AccountViewModel.UiActionAccount.Logout -> logout()
                AccountViewModel.UiActionAccount.OpenTelegram -> openTelegram()
            }
        }

        viewModel.getAccountErrorStateFlow().observeFlow(this) { errorAccount ->
            if (errorAccount) updateAccountError()
        }
    }

    private fun openTelegram() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM_KURSBIO_BOT_URL)))
    }

    private fun logout() {
        PrefsSettings.setAccountLogin("")
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
