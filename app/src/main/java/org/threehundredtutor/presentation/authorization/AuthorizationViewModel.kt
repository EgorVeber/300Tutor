package org.threehundredtutor.presentation.authorization

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.data.common.network.ErrorType
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.authorization.LoginDateModel
import org.threehundredtutor.domain.authorization.LoginModel
import org.threehundredtutor.domain.authorization.LoginUseCase
import org.threehundredtutor.domain.common.SetAccountInfoUseCase
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setAccountInfoUseCase: SetAccountInfoUseCase,
    private val getAccountUseCase: GetAccountUseCase,
) : BaseViewModel() {

    private val openScreenEventState = SingleSharedFlow<NavigateScreenState>()
    private val errorEventState = SingleSharedFlow<String>()
    private val inputTypeState = MutableStateFlow<InputTypeState>(InputTypeState.Email)
    private val loadingState = MutableStateFlow(false)

    fun getOpenScreenEventStateFlow() = openScreenEventState.asSharedFlow()
    fun getErrorEventStateFlow() = errorEventState.asSharedFlow()
    fun getInputTypeStateFlow() = inputTypeState.asSharedFlow()
    fun getLoadingStateFlow() = loadingState.asSharedFlow()

    fun onSignInButtonClicked(password: String, email: String, phone: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val emailOrPhone = if (inputTypeState.value is InputTypeState.Phone) phone else email
            val loginModel = loginUseCase(LoginDateModel(password, false, emailOrPhone))
            if (loginModel.succeeded) {
                val accountInfo = getAccountUseCase()
                setAccountInfoUseCase.invoke(
                    login = emailOrPhone,
                    password = password,
                    userId = accountInfo.userId
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
            ErrorType.AlreadyAuthenticated,
            ErrorType.EMAIL_SHOULD_BE_SET,
            ErrorType.PHONE_NUMBER_SHOULD_BE_SET,
            ErrorType.USER_EMAIL_ALREADY_EXISTS,
            ErrorType.USER_PHONE_ALREADY_EXISTS,
            ErrorType.USER_MANAGER_ERROR,
            ErrorType.CLIENT_ADDING_ERROR,
            ErrorType.UNACCEPTABLE_PASSWORD,
            ErrorType.NONE,
            -> errorEventState.tryEmit(loginModel.errorMessage + loginModel.errorType.name)
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
