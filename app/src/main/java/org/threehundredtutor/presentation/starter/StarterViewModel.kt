package org.threehundredtutor.presentation.starter

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.authorization.LoginParamsModel
import org.threehundredtutor.domain.authorization.LoginUseCase
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.domain.starter.GetAccountAuthorizationInfoUseCase
import org.threehundredtutor.domain.starter.GetFirstStartAppUseCase
import org.threehundredtutor.domain.starter.SetFirstStartAppUseCase
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class StarterViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getAccountAuthorizationInfoUseCase: GetAccountAuthorizationInfoUseCase,
    private val getFirstStartAppUseCase: GetFirstStartAppUseCase,
    private val setFirstStartAppUseCase: SetFirstStartAppUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val getConfigUseCase: GetConfigUseCase,
) : BaseViewModel() {

    private val uiEventState = MutableStateFlow<UiEvent>(UiEvent.Empty)

    private val isFirstStartApp = getFirstStartAppUseCase()

    init {
        viewModelScope.launchJob(tryBlock = {
            getSettingAppUseCase(true)
            val (login, password) = getAccountAuthorizationInfoUseCase.invoke()
            when {
                isFirstStartApp -> {
                    uiEventState.tryEmit(UiEvent.NavigateRegistrationScreen)
                    setFirstStartAppUseCase.invoke()
                }

                login.isNotEmpty() && password.isNotEmpty() -> {
                    val loginModel = loginUseCase.invoke(
                        LoginParamsModel(
                            password = password,
                            rememberMe = true,
                            emailOrPhoneNumber = login
                        )
                    )
                    if (loginModel.succeeded || loginModel.errorType == ErrorType.ALREADY_AUTHENTICATED) {
                        getAccountUseCase(true)
                        uiEventState.tryEmit(UiEvent.NavigateHomeScreen)
                    } else {
                        uiEventState.tryEmit(UiEvent.NavigateAuthorizationScreen)
                    }
                }

                else -> {
                    uiEventState.tryEmit(UiEvent.NavigateAuthorizationScreen)
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable) {
                uiEventState.tryEmit(UiEvent.NavigateAuthorizationScreen)
            }
        })
    }

    fun getUiEventStateFlow() = uiEventState.asStateFlow()

    sealed interface UiEvent {
        object NavigateRegistrationScreen : UiEvent
        object NavigateAuthorizationScreen : UiEvent
        object NavigateHomeScreen : UiEvent
        object Empty : UiEvent
    }
}