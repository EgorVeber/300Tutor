package org.threehundredtutor.presentation.authorization

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asSharedFlow
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.data.core.models.ErrorType
import org.threehundredtutor.domain.authorization.LoginDateModel
import org.threehundredtutor.domain.authorization.LoginModel
import org.threehundredtutor.domain.authorization.LoginUseCase

class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase = LoginUseCase(),
) : BaseViewModel() {

    private val openScreenEventState = SingleSharedFlow<NavigateScreenState>()
    private val errorEventState = SingleSharedFlow<String>()

    fun getOpenScreenEventStateFlow() = openScreenEventState.asSharedFlow()

    fun getErrorEventStateFlow() = errorEventState.asSharedFlow()

    fun onSignInButtonClicked(password: String, email: String) {
        viewModelScope.launchJob(tryBlock = {
            val loginModel = loginUseCase(LoginDateModel(password, false, email))
            if (loginModel.succeeded) openScreenEventState.emit(NavigateScreenState.NavigateHomeScreen)
            else handleError(loginModel)
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onRegistrationButtonClicked() {
        openScreenEventState.tryEmit(NavigateScreenState.NavigateRegistrationScreen)
    }

    private fun handleError(loginModel: LoginModel) {
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
            ErrorType.NONE -> errorEventState.tryEmit(loginModel.errorMessage + loginModel.errorType.name)
        }
    }

    sealed class NavigateScreenState() {
        object NavigateRegistrationScreen : NavigateScreenState()
        object NavigateHomeScreen : NavigateScreenState()
    }
}
