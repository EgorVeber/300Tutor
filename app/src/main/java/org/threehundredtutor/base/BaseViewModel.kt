package org.threehundredtutor.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.threehundredtutor.base.network.BadGatewayException
import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.base.network.ForbiddenServerException
import org.threehundredtutor.base.network.GatewayTimeoutException
import org.threehundredtutor.base.network.InternalServerException
import org.threehundredtutor.base.network.NotFoundServerException
import org.threehundredtutor.base.network.ServiceUnavailableException
import org.threehundredtutor.base.network.UnauthorizedUserException
import org.threehundredtutor.base.network.UnknownServerException
import org.threehundredtutor.common.extentions.SingleSharedFlow

open class BaseViewModel : ViewModel() {

    private val errorState = SingleSharedFlow<ErrorState>()

    fun getErrorState(): SharedFlow<ErrorState> = errorState.asSharedFlow()

    open fun handleError(throwable: Throwable) {
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
    }

    sealed interface ErrorState {
        object WrongData : ErrorState
        object ServerError : ErrorState
        object UserUnathorized : ErrorState
        object SomethingWrongError : ErrorState
        object NoError : ErrorState
    }
}
