package org.threehundredtutor.presentation.starter

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.authorization.LoginDateModel
import org.threehundredtutor.domain.authorization.LoginUseCase
import org.threehundredtutor.domain.starter.GetAccountInfoUseCase
import org.threehundredtutor.domain.starter.GetFirstStartAppUseCase
import org.threehundredtutor.domain.starter.GetThemePrefsUseCase
import org.threehundredtutor.domain.starter.SetFirstStartAppUseCase
import javax.inject.Inject

class StarterViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getAccountInfoUseCase: GetAccountInfoUseCase,
    private val getFirstStartAppUseCase: GetFirstStartAppUseCase,
    private val setFirstStartAppUseCase: SetFirstStartAppUseCase,
    private val getThemePrefsUseCase: GetThemePrefsUseCase,
) : BaseViewModel() {

    private val uiEventState = MutableStateFlow<UiEvent>(UiEvent.Empty)

    init {
        viewModelScope.launchJob(tryBlock = {
            val (login, password) = getAccountInfoUseCase.invoke()
            when {
                getFirstStartAppUseCase.invoke() -> {
                    uiEventState.tryEmit(UiEvent.NavigateRegistrationScreen)
                    setFirstStartAppUseCase.invoke()
                }

                login.isNotEmpty() && password.isNotEmpty() -> {
                    val loginModel = loginUseCase.invoke(LoginDateModel(password, false, login))
                    if (loginModel.succeeded) {
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
            handleError(throwable)
        })
    }

    fun getUiEventStateFlow() = uiEventState.asStateFlow()
    fun onCreateActivity(): Int = getThemePrefsUseCase.invoke()

    sealed interface UiEvent {
        object NavigateRegistrationScreen : UiEvent
        object NavigateAuthorizationScreen : UiEvent
        object NavigateHomeScreen : UiEvent
        object Empty : UiEvent
    }
}