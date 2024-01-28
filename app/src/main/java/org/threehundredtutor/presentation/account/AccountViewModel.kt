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
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.usecase.CreateLoginLinkResultUseCase
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.account.usecase.LogoutUseCase
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val accountUiEventState = SingleSharedFlow<AccountUiEvent>()

    private val accountInfoState = MutableStateFlow(AccountModel.EMPTY)
    private val accountErrorState = MutableStateFlow(false)

    init {
        getAccountInfo()
    }

    fun getAccountInfoStateFlow() = accountInfoState.asStateFlow()
    fun getLoadingStateStateFlow() = loadingState.asStateFlow()
    fun getaccountUiEventState() = accountUiEventState.asSharedFlow()
    fun getAccountErrorStateFlow() = accountErrorState.asSharedFlow()

    private fun getAccountInfo() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val accountInfo = getAccountUseCase()
            if (accountInfo.isEmpty()) throw UnknownServerException()
            accountInfoState.update { accountInfo }
        }, catchBlock = { throwable ->
            handleError(throwable) {
                accountErrorState.update { true }
            }
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onLogoutClick() {
        viewModelScope.launchJob(tryBlock = {
            val isSucceeded = logoutUseCase().isSucceeded
            if (!isSucceeded) throw UnknownServerException()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            accountUiEventState.tryEmit(AccountUiEvent.Logout)
        })
    }

    fun onTelegramClicked() {
        accountUiEventState.tryEmit(AccountUiEvent.OpenTelegram)
    }

    fun onSiteClicked() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = createLoginLinkResultUseCase.invoke()
            if (result.isSucceeded) {
                accountUiEventState.emit(
                    AccountUiEvent.OpenSite(urlAuthentication = result.urlAuthentication)
                )
            } else {
                accountUiEventState.emit(AccountUiEvent.ShowMessage(result.errorMessage))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    sealed class AccountUiEvent {
        data class OpenSite(val urlAuthentication: String) : AccountUiEvent()
        data class ShowMessage(val message: String) : AccountUiEvent()
        object OpenTelegram : AccountUiEvent()
        object Logout : AccountUiEvent()
    }
}
