package com.v_points.app.presentation.starter

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.v_points.app.data.common.network.ErrorType
import com.v_points.app.domain.account.usecase.GetAccountUseCase
import com.v_points.app.domain.authorization.LoginParamsModel
import com.v_points.app.domain.authorization.LoginUseCase
import com.v_points.app.domain.common.GetConfigUseCase
import com.v_points.app.domain.settings_app.GetSettingAppUseCase
import com.v_points.app.domain.starter.GetAccountAuthorizationInfoUseCase
import com.v_points.app.domain.starter.GetFirstStartAppUseCase
import com.v_points.app.domain.starter.SetFirstStartAppUseCase
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
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