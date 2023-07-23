package org.threehundredtutor.presentation.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.usecases.RegistrationUseCase

class RegistrationViewModel : ViewModel() {

    private val registrationState = MutableStateFlow(RegistrationModel.empty())

    private val errorState = MutableSharedFlow<String>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val registrationUseCase: RegistrationUseCase = RegistrationUseCase()
    fun getRegistrationState(): Flow<RegistrationModel> = registrationState
    fun getErrorState(): SharedFlow<String> = errorState

    fun register(
        email: String,
        name: String,
        surname: String,
        patronymic: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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
                errorState.tryEmit(result.errorMessage)
            }
        }
    }
}
