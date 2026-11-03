package com.v_points.app.vpoint.participants.presentation

import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.vpoint.participants.domain.GetParticipantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ParticipantsViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getParticipantsUseCase: GetParticipantsUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableParticipantsState()
    private val loadingState = MutableStateFlow(false)

    fun getMainState(): MutableParticipantsState = uiItemsState
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        loadListData()
    }

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val users = getParticipantsUseCase()
            // uiItemsState.users = users.mapIndexed { index, model ->
            //     model.toUserUiModel(index)
            // }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }
}