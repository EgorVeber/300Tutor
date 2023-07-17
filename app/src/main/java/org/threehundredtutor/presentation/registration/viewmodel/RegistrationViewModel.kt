package org.threehundredtutor.presentation.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.usecases.RegistrationUseCase

class RegistrationViewModel : ViewModel() {

    private val registrationState = MutableStateFlow(RegistrationModel.empty())

    private val registrationUseCase: RegistrationUseCase = RegistrationUseCase()
    fun getRegistrationState(): Flow<RegistrationModel> = registrationState

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
            }
        }
    }
}
