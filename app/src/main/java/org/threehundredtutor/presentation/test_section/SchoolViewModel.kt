package org.threehundredtutor.presentation.test_section

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.account.usecase.LogoutUseCase
import org.threehundredtutor.domain.authorization.LoginParamsModel
import org.threehundredtutor.domain.authorization.LoginUseCase
import org.threehundredtutor.domain.common.SetAccountAuthorizationInfUseCase
import org.threehundredtutor.domain.school.GetAccountAuthInfoByDomainScenario
import org.threehundredtutor.domain.school.GetDomainUseCase
import org.threehundredtutor.domain.school.GetSchoolUseCase
import org.threehundredtutor.domain.school.SetDomainUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SchoolViewModel @Inject constructor(
    getSchoolUseCase: GetSchoolUseCase,
    private val resourceProvider: ResourceProvider,
    private val getDomainUseCase: GetDomainUseCase,
    private val setDomainUseCase: SetDomainUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAccountAuthInfoByDomainScenario: GetAccountAuthInfoByDomainScenario,
    private val loginUseCase: LoginUseCase,
    private val setAccountAuthorizationInfUseCase: SetAccountAuthorizationInfUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SchoolUiModel>>(listOf())
    private val messagesStream = SingleSharedFlow<String>()
    private val navigationEvent = SingleSharedFlow<NavigationEvent>()
    private val loadingState = MutableStateFlow(false)

    private val currentDomainSettings = getDomainUseCase()

    init {
        viewModelScope.launchJob(tryBlock = {
            uiItemsState.update {
                getSchoolUseCase().map { schoolItem ->
                    schoolItem.toSchoolUiModel(currentDomainSettings == schoolItem.hostUrl)
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun getUiItems(): Flow<List<SchoolUiModel>> = uiItemsState
    fun getMessagesStream(): Flow<String> = messagesStream
    fun getUiAction(): Flow<NavigationEvent> = navigationEvent
    fun getLoadingState(): Flow<Boolean> = loadingState

    fun onSchoolClicked(domain: String, name: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            runCatching {
                logoutUseCase.invoke()
            }.onFailure { throwable ->
                loadingState.update { false }
                handleError(throwable)
                return@launchJob
            }

            runCatching {
                setDomainUseCase(domain)
                selectCurrentSchool(domain)
                getSettingAppUseCase(true)
                messagesStream.tryEmit(resourceProvider.string(UiCoreStrings.domain_change, name))
            }.onFailure {
                setDomainUseCase(currentDomainSettings)
                messagesStream.tryEmit(
                    resourceProvider.string(UiCoreStrings.domain_change_error, name)
                )
                navigationEvent.tryEmit(NavigationEvent.StartedScreen)
                loadingState.update { false }
                return@launchJob
            }

            val account = getAccountAuthInfoByDomainScenario(domain)
            if (account != null) {
                runCatching {
                    val result = loginUseCase(
                        LoginParamsModel(
                            password = account.password,
                            emailOrPhoneNumber = account.login,
                            rememberMe = true
                        )
                    )
                    if (result.succeeded || result.errorType == ErrorType.ALREADY_AUTHENTICATED) {
                        setAccountAuthorizationInfUseCase.invoke(
                            login = account.login,
                            password = account.password,
                        )
                        navigationEvent.tryEmit(NavigationEvent.HomeScreen)
                    } else {
                        messagesStream.tryEmit(resourceProvider.string(UiCoreStrings.domain_change_and_auth_error))
                        navigationEvent.tryEmit(NavigationEvent.AuthorizationScreen)
                    }
                }.onFailure { throwable ->
                    navigationEvent.tryEmit(NavigationEvent.AuthorizationScreen)
                    handleError(throwable)
                }
            } else {
                navigationEvent.tryEmit(NavigationEvent.AuthorizationScreen)
            }
        }, catchBlock = {
            handleError(it)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    private fun selectCurrentSchool(host: String) {
        uiItemsState.update { uiItems ->
            uiItems.map { uiItem ->
                if (uiItem.hostUrl == host) {
                    uiItem.copy(checked = true)
                } else {
                    uiItem.copy(checked = false)
                }
            }
        }
    }

    sealed interface NavigationEvent {
        object HomeScreen : NavigationEvent
        object StartedScreen : NavigationEvent
        object AuthorizationScreen : NavigationEvent
    }
}