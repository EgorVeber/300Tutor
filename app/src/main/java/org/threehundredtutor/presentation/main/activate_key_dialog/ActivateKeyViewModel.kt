package org.threehundredtutor.presentation.main.activate_key_dialog

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.main.usecase.EnterGroupUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util.addQuotesSymbol
import javax.inject.Inject

class ActivateKeyViewModel @Inject constructor(
    private val enterGroupUseCase: EnterGroupUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val loadingState = MutableStateFlow(false)

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    fun onActivateKeyClicked(key: String) {
        if (key.isEmpty()) return
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val result = enterGroupUseCase.invoke(key)
            if (result.succeeded) {
                uiEventState.tryEmit(
                    UiEvent.SendResultListener(
                        success = true,
                        message = resourceProvider.string(
                            UiCoreStrings.activate_key_message,
                            result.groupName.addQuotesSymbol()
                        )
                    )
                )
            } else {
                uiEventState.tryEmit(
                    UiEvent.SendResultListener(
                        success = false,
                        message = result.errorMessage,
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    sealed interface UiEvent {
        data class SendResultListener(
            val success: Boolean,
            val message: String,
        ) : UiEvent
    }
}
