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
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.domain.settings_app.TelegramBotSettingsModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util.UnknownServerException
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val accountUiEventState = SingleSharedFlow<AccountUiEvent>()

    private val accountInfoState = MutableStateFlow(AccountModel.EMPTY)
    private val accountErrorState = MutableStateFlow(false)
    private val telegramVisibleState = MutableStateFlow(false)

    init {
        viewModelScope.launchJob(tryBlock = {
            val settingsAppModel = getSettingAppUseCase(false)
            if (settingsAppModel.telegramBotSettingsModel.hasBot && settingsAppModel.telegramBotSettingsModel != TelegramBotSettingsModel.empty()) {
                telegramVisibleState.tryEmit(true)
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
        getAccountInfo()
    }

    fun getAccountInfoStateFlow() = accountInfoState.asStateFlow()
    fun getLoadingStateStateFlow() = loadingState.asStateFlow()
    fun getAccountUiEventState() = accountUiEventState.asSharedFlow()
    fun getAccountErrorStateFlow() = accountErrorState.asSharedFlow()
    fun getTelegramVisibleState() = telegramVisibleState.asSharedFlow()

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
            if (isSucceeded) {
                accountUiEventState.tryEmit(AccountUiEvent.Logout)
            } else {
                throw UnknownServerException()
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onTelegramClicked() {
        viewModelScope.launchJob(tryBlock = {
            val botName = getSettingAppUseCase(false).telegramBotSettingsModel.botName
            accountUiEventState.tryEmit(
                AccountUiEvent.OpenTelegram(
                    telegramBotUrl = TELEGRAM_DOMAIN + botName,
                    telegramBotName = botName
                )
            )
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onSiteClicked() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result =
                createLoginLinkResultUseCase.invoke(
                    siteUrl = getSettingAppUseCase(false).applicationUrl,
                    redirectLink = EMPTY_STRING
                )
            if (result.isSucceeded) {
                accountUiEventState.emit(
                    AccountUiEvent.OpenSite(
                        urlAuthentication = result.urlAuthentication,
                    )
                )
            } else {
                accountUiEventState.emit(
                    AccountUiEvent.OpenSite(
                        urlAuthentication = getSettingAppUseCase(false).applicationUrl,
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    sealed interface AccountUiEvent {
        @JvmInline
        value class OpenSite(val urlAuthentication: String) : AccountUiEvent

        data class ShowMessage(val message: String) : AccountUiEvent

        data class OpenTelegram(val telegramBotUrl: String, val telegramBotName: String) :
            AccountUiEvent

        object Logout : AccountUiEvent
    }

    companion object {
        const val TELEGRAM_DOMAIN = "https://telegram.me/"
    }
}