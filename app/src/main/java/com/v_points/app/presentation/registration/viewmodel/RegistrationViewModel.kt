package com.v_points.app.presentation.registration.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import com.v_points.app.domain.common.SetAccountAuthorizationInfUseCase
import com.v_points.app.domain.registration.models.RegistrationAccountAndSignInModel
import com.v_points.app.domain.registration.models.RegistrationStudentAndSignInModel
import com.v_points.app.domain.registration.usecases.RegistrationStudentUseCase
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationStudentUseCase: RegistrationStudentUseCase,
    private val setAccountAuthorizationInfUseCase: SetAccountAuthorizationInfUseCase,
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

    fun registerStudent(
        email: String,
        name: String,
        surname: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            val result = registrationStudentUseCase.invoke(
                email = email,
                name = name,
                surname = surname,
                phoneNumber = phoneNumber,
                password = password
            )
            if (result.succeeded) {
                setAccountAuthorizationInfUseCase(email, password)
                registrationStudentState.emit(result)
            } else {
                resultNotSuccessFlow.tryEmit(result.message)
            }
        }, catchBlock = { error ->
            handleError(error)
        })
    }
}
