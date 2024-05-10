package org.threehundredtutor.presentation.account

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.usecase.CreateLoginLinkResultUseCase
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.account.usecase.LogoutUseCase
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util.UnknownServerException
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    getConfigUseCase: GetConfigUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val accountUiEventState = SingleSharedFlow<AccountUiEvent>()

    private val accountInfoState = MutableStateFlow(AccountModel.EMPTY)
    private val accountErrorState = MutableStateFlow(false)

    private val localConfig = getConfigUseCase()

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
            val accountInfo = getAccountUseCase(true)
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
        accountUiEventState.tryEmit(
            AccountUiEvent.OpenTelegram(
                telegramBotUrl = localConfig.telegramBotUrl
            )
        )
    }

    fun onSiteClicked() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = createLoginLinkResultUseCase.invoke(localConfig.siteUrl)
            if (result.isSucceeded) {
                accountUiEventState.emit(
                    AccountUiEvent.OpenSite(
                        urlAuthentication = result.urlAuthentication,
                        siteUrl = localConfig.siteUrl
                    )
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

    sealed interface AccountUiEvent {
        data class OpenSite(val urlAuthentication: String, val siteUrl: String) : AccountUiEvent
        data class ShowMessage(val message: String) : AccountUiEvent

        @JvmInline
        value class OpenTelegram(val telegramBotUrl: String) : AccountUiEvent
        object Logout : AccountUiEvent
    }
}
