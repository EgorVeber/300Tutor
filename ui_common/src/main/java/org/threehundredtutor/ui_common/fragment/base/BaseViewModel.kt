package org.threehundredtutor.ui_common.fragment.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.util.BadGatewayException
import org.threehundredtutor.ui_common.util.BadRequestException
import org.threehundredtutor.ui_common.util.ForbiddenServerException
import org.threehundredtutor.ui_common.util.GatewayTimeoutException
import org.threehundredtutor.ui_common.util.InternalServerException
import org.threehundredtutor.ui_common.util.NotFoundServerException
import org.threehundredtutor.ui_common.util.ServiceUnavailableException
import org.threehundredtutor.ui_common.util.UnauthorizedUserException
import org.threehundredtutor.ui_common.util.UnknownServerException

open class BaseViewModel : ViewModel() {

    private val errorState = SingleSharedFlow<ErrorState>()

    fun getErrorState(): SharedFlow<ErrorState> = errorState.asSharedFlow()

    open fun handleError(throwable: Throwable, errorAction: (() -> Unit)? = null) {
        when (throwable) {
            is BadRequestException, is ForbiddenServerException, is NotFoundServerException -> {
                errorState.tryEmit(ErrorState.WrongData)
            }

            is UnauthorizedUserException -> {
                errorState.tryEmit(ErrorState.UserUnathorized)
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

    sealed interface ErrorState {
        object WrongData : ErrorState
        object ServerError : ErrorState
        object UserUnathorized : ErrorState
        object SomethingWrongError : ErrorState
        object NoError : ErrorState
    }
}
