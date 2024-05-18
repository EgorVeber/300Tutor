package org.threehundredtutor.presentation.starter

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.authorization.LoginParamsModel
import org.threehundredtutor.domain.authorization.LoginUseCase
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
) : BaseViewModel() {

    private val uiEventState = MutableStateFlow<UiEvent>(UiEvent.Empty)

    init {
        uiEventState.tryEmit(UiEvent.NavigateAuthorizationScreen)

        viewModelScope.launchJob(tryBlock = {
            val (login, password) = getAccountAuthorizationInfoUseCase.invoke()
            when {
                getFirstStartAppUseCase.invoke() -> {
                    uiEventState.tryEmit(UiEvent.NavigateRegistrationScreen)
                    setFirstStartAppUseCase.invoke()
                }

                login.isNotEmpty() && password.isNotEmpty() -> {
                    val loginModel = loginUseCase.invoke(
                        LoginParamsModel(
                            password = password,
                            rememberMe = false,
                            emailOrPhoneNumber = login
                        )
                    )
                    if (loginModel.succeeded || loginModel.errorType == ErrorType.AlreadyAuthenticated) {
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