package org.threehundredtutor.ui_common.fragment.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.ui_common.util.BadGatewayException
import org.threehundredtutor.ui_common.util.BadRequestException
import org.threehundredtutor.ui_common.util.ForbiddenServerException
import org.threehundredtutor.ui_common.util.GatewayTimeoutException
import org.threehundredtutor.ui_common.util.InternalServerException
import org.threehundredtutor.ui_common.util.NotFoundServerException
import org.threehundredtutor.ui_common.util.ServiceUnavailableException
import org.threehundredtutor.ui_common.util.UnauthorizedUserException
import org.threehundredtutor.ui_common.util.UnknownServerException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    private val errorState = MutableStateFlow<ErrorState>(ErrorState.NoError)

    fun getErrorState(): Flow<ErrorState> = errorState

    open fun handleError(throwable: Throwable, errorAction: (() -> Unit)? = null) {
        when (throwable) {
            is BadRequestException, is ForbiddenServerException, is NotFoundServerException -> {
                errorState.tryEmit(ErrorState.WrongData)
            }

            is UnauthorizedUserException -> {
                errorState.tryEmit(ErrorState.UserUnathorized)
            }

            is UnknownHostException -> {
                errorState.tryEmit(ErrorState.UnknownHostException)
            }

            is InternalServerException,
            is BadGatewayException,
            is ServiceUnavailableException,
            is GatewayTimeoutException,
            is UnknownServerException -> {
                errorState.tryEmit(ErrorState.ServerError)
            }

            else -> {
                errorState.tryEmit(ErrorState.SomethingWrongError)
            }
        }
        errorAction?.invoke()
    }

    fun updateState() {
        errorState.update { ErrorState.NoError }
    }

    sealed interface ErrorState {
        object WrongData : ErrorState
        object ServerError : ErrorState
        object UserUnathorized : ErrorState
        object UnknownHostException : ErrorState
        object SomethingWrongError : ErrorState
        object NoError : ErrorState
    }
}
