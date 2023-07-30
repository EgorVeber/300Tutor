package org.threehundredtutor.presentation.registration.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.usecases.RegistrationUseCase

class RegistrationViewModel : BaseViewModel() {

    private val registrationState = MutableStateFlow(RegistrationModel.empty())

    private val resultNotSuccededFlow = SingleSharedFlow<String>()

    private val registrationUseCase: RegistrationUseCase = RegistrationUseCase()
    fun getRegistrationState(): Flow<RegistrationModel> = registrationState
    fun getResultNotSuccededFlow(): SharedFlow<String> = resultNotSuccededFlow

    fun register(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launchJob(
            tryBlock = {
                val result = registrationUseCase.invoke(
                    email = email,
                    name = name,
                    surname = surname,
                    patronymic = patronymic,
                    phoneNumber = phoneNumber,
                    password = password
                )
                if (result.succeded) {
                    registrationState.emit(result)
                } else {
                    resultNotSuccededFlow.tryEmit(result.errorMessage)
                }
            }, catchBlock = { error ->
                handleError(error)
            }
        )
    }
}
