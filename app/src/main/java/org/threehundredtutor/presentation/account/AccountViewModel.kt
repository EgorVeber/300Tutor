package org.threehundredtutor.presentation.account

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.base.network.UnknownServerException
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.account.AccountModel
import org.threehundredtutor.domain.account.GetAccountUseCase
import org.threehundredtutor.domain.account.LogoutUseCase
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

    private val accountState = MutableStateFlow(AccountModel.EMPTY)
    private val loadingState = MutableStateFlow(false)
    private val accountErrorState = MutableStateFlow(false)
    private val actionAccountState = SingleSharedFlow<UiActionAccount>()

    init {
        getAccountInfo()
    }

    fun getAccountStateFlow() = accountState.asStateFlow()
    fun getLoadingStateStateFlow() = loadingState.asStateFlow()
    fun getActionAccountSharedFlow() = actionAccountState.asSharedFlow()
    fun getAccountErrorStateFlow() = accountErrorState.asSharedFlow()

    private fun getAccountInfo() {
        loadingState.update { true }
        viewModelScope.launchJob(tryBlock = {
            val accountInfo = getAccountUseCase()
            if (accountInfo.isEmpty()) throw UnknownServerException()
            accountState.update { accountInfo }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    override fun handleError(throwable: Throwable) {
        if (throwable is UnknownServerException) {
            accountErrorState.update { true }
        }
        super.handleError(throwable)
    }

    fun onLogoutClick() {
        viewModelScope.launchJob(tryBlock = {
            val isSucceeded = logoutUseCase().isSucceeded
            if (!isSucceeded) throw UnknownServerException()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            actionAccountState.tryEmit(UiActionAccount.Logout)
        })
    }

    fun onTelegramClicked() {
        actionAccountState.tryEmit(UiActionAccount.OpenTelegram)
    }

    sealed class UiActionAccount {
        object OpenTelegram : UiActionAccount()
        object Logout : UiActionAccount()
    }
}
