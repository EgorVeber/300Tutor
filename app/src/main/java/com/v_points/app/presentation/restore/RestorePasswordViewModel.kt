package com.v_points.app.presentation.restore

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.domain.restore.ForgotStartUseCase
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.SnackBarType
import javax.inject.Inject

class RestorePasswordViewModel @Inject constructor(
    private val forgotStartUseCase: ForgotStartUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val messageStream = SingleSharedFlow<Pair<String, SnackBarType>>()
    private val dismissAction = SingleSharedFlow<Unit>()
    fun getMessageStream(): Flow<Pair<String, SnackBarType>> = messageStream
    fun getDismissAction(): Flow<Unit> = dismissAction
    fun onSendButtonClicked(email: String) {
        viewModelScope.launchJob(tryBlock = {
            email.ifEmpty { return@launchJob }
            val result = forgotStartUseCase.invoke(email)
            if (result.isSucceeded) {
                messageStream.tryEmit(
                    resourceProvider.string(
                        UiCoreStrings.reset_password_send_description,
                        email
                    ) to SnackBarType.SUCCESS
                )
                dismissAction.tryEmit(Unit)
            } else {
                messageStream.tryEmit(result.message to SnackBarType.INFO)
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }
}
