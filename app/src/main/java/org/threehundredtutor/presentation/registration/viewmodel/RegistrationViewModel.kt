package org.threehundredtutor.presentation.registration.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.threehundredtutor.domain.common.SetAccountInfoUseCase
import org.threehundredtutor.domain.registration.models.RegistrationAccountAndSignInModel
import org.threehundredtutor.domain.registration.models.RegistrationStudentAndSignInModel
import org.threehundredtutor.domain.registration.usecases.RegistrationAccountUseCase
import org.threehundredtutor.domain.registration.usecases.RegistrationStudentUseCase
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationAccountUseCase: RegistrationAccountUseCase,
    private val registrationStudentUseCase: RegistrationStudentUseCase,
    private val setAccountInfoUseCase: SetAccountInfoUseCase,
) : BaseViewModel() {

    private val registrationAccountState =
        MutableStateFlow(RegistrationAccountAndSignInModel.empty())

    private val registrationStudentState =
        MutableStateFlow(RegistrationStudentAndSignInModel.empty())

    private val resultNotSuccessFlow = SingleSharedFlow<String>()

    fun getRegistrationAccountState(): Flow<RegistrationAccountAndSignInModel> =
        registrationAccountState

    fun getRegistrationStudentState(): Flow<RegistrationStudentAndSignInModel> =
        registrationStudentState

    fun getResultNotSuccessFlow(): SharedFlow<String> = resultNotSuccessFlow

    fun registerAccount(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            val result = registrationAccountUseCase.invoke(
                email = email,
                name = name,
                surname = surname,
                patronymic = patronymic,
                phoneNumber = phoneNumber,
                password = password
            )
            val userId = result.registrationModel.registeredUser.id
            if (result.registrationModel.succeded && result.loginModel.succeeded) {
                setAccountInfoUseCase(email, password, userId)
                registrationAccountState.emit(result)
            } else {
                if (result.registrationModel.errorMessage.isNotEmpty()) {
                    resultNotSuccessFlow.tryEmit(result.registrationModel.errorMessage)
                } else {
                    setAccountInfoUseCase(email, password, userId)
                    resultNotSuccessFlow.tryEmit(result.loginModel.errorMessage)
                }
            }
        }, catchBlock = { error ->
            handleError(error)
        })
    }

    fun registerStudent(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            val result = registrationStudentUseCase.invoke(
                email = email,
                name = name,
                surname = surname,
                patronymic = patronymic,
                phoneNumber = phoneNumber,
                password = password
            )
            if (result.succeeded) {
                setAccountInfoUseCase(email, password, result.studentId)
                registrationStudentState.emit(result)
            } else {
                resultNotSuccessFlow.tryEmit(result.message)
            }
        }, catchBlock = { error ->
            handleError(error)
        })
    }
}
