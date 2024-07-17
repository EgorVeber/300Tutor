package org.threehundredtutor.presentation.restore

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.restore.ForgotStartUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_core.SnackBarType
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
