package org.threehundredtutor.presentation.authorization

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.authorization.LoginModel
import org.threehundredtutor.domain.authorization.LoginParamsModel
import org.threehundredtutor.domain.authorization.LoginUseCase
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.common.SetAccountAuthorizationInfUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppStreamUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setAccountAuthorizationInfUseCase: SetAccountAuthorizationInfUseCase,
    private val getConfigUseCase: GetConfigUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val resourceProvider: ResourceProvider,
    private val getSettingAppStreamUseCase: GetSettingAppStreamUseCase,
) : BaseViewModel() {

    private val openScreenEventState = SingleSharedFlow<NavigateScreenState>()
    private val errorEventState = SingleSharedFlow<String>()
    private val inputTypeState = MutableStateFlow<InputTypeState>(InputTypeState.Email)
    private val loadingState = MutableStateFlow(false)
    private val changeDomainState = MutableStateFlow(getConfigUseCase().changeDomain)
    private val schoolName = MutableStateFlow("")

    init {
        viewModelScope.launchJob(tryBlock = {
            if (changeDomainState.value) {
                getSettingAppStreamUseCase().onEach {
                    val setting = getSettingAppUseCase(false)
                    schoolName.emit(
                        resourceProvider.string(
                            UiCoreStrings.school_change_title,
                            setting.applicationName
                        )
                    )
                }.launchIn(this)
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun getSchoolNameState() = schoolName.asSharedFlow()
    fun getChangeDomainState() = changeDomainState.asSharedFlow()
    fun getOpenScreenEventStateFlow() = openScreenEventState.asSharedFlow()
    fun getErrorEventStateFlow() = errorEventState.asSharedFlow()
    fun getInputTypeStateFlow() = inputTypeState.asSharedFlow()
    fun getLoadingStateFlow() = loadingState.asSharedFlow()
    fun onSignInButtonClicked(password: String, email: String, phone: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val emailOrPhone = if (inputTypeState.value is InputTypeState.Phone) phone else email
            val loginModel = loginUseCase(
                LoginParamsModel(
                    password = password,
                    rememberMe = true,
                    emailOrPhoneNumber = emailOrPhone
                )
            )
            if (loginModel.succeeded || loginModel.errorType == ErrorType.ALREADY_AUTHENTICATED) {
                setAccountAuthorizationInfUseCase.invoke(
                    login = emailOrPhone,
                    password = password,
                )
                openScreenEventState.emit(NavigateScreenState.NavigateHomeScreen)
            } else extractError(loginModel)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onRegistrationButtonClicked() {
        openScreenEventState.tryEmit(NavigateScreenState.NavigateRegistrationScreen)
    }

    private fun extractError(loginModel: LoginModel) {
        when (loginModel.errorType) {
            ErrorType.REGISTRATION_NOT_ENABLED,
            ErrorType.ALREADY_AUTHENTICATED,
            ErrorType.EMAIL_SHOULD_BE_SET,
            ErrorType.PHONE_NUMBER_SHOULD_BE_SET,
            ErrorType.USER_EMAIL_ALREADY_EXISTS,
            ErrorType.USER_PHONE_ALREADY_EXISTS,
            ErrorType.USER_MANAGER_ERROR,
            ErrorType.CLIENT_ADDING_ERROR,
            ErrorType.UNACCEPTABLE_PASSWORD,
            ErrorType.NONE,
            -> errorEventState.tryEmit(loginModel.errorMessage)
        }
    }

    fun onImageClicked(visible: Boolean) {
        if (visible) inputTypeState.update { InputTypeState.Phone }
        else inputTypeState.update { InputTypeState.Email }
    }

    sealed class NavigateScreenState {
        object NavigateHomeScreen : NavigateScreenState()
        object NavigateRegistrationScreen : NavigateScreenState()
    }

    sealed class InputTypeState {
        object Phone : InputTypeState()
        object Email : InputTypeState()
    }
}