package com.v_points.app.vpoint.game_day_create.presentation

import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.vpoint.fiersote.data.repository.GymsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

sealed interface CreateGameDayNavigateAction {
    data object Back : CreateGameDayNavigateAction
    data object SuccessCreated : CreateGameDayNavigateAction
}

class CreateGameDayViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val gymsRepository: GymsRepository
) : BaseViewModel() {

    private val state = MutableCreateGameDayState()
    private val loadingState = MutableStateFlow(false)
    private val navigateAction = SingleSharedFlow<CreateGameDayNavigateAction>()

    fun getState(): CreateGameDayState = state
    fun getLoadingState() = loadingState.asStateFlow()
    fun getNavigateAction(): Flow<CreateGameDayNavigateAction> = navigateAction

    init {
        loadGyms()
    }

    private fun loadGyms() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            state.gyms = gymsRepository.getGyms()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    fun onAction(action: CreateGameDayAction) {
        when (action) {
            is CreateGameDayAction.OnDateClick -> {
                state.isDateDialogVisible = true
            }
            is CreateGameDayAction.OnDateSelected -> {
                state.isDateDialogVisible = false
                state.selectedTimestamp = action.timestamp
                if (action.timestamp != null) {
                    val formatted = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
                        .format(Date(action.timestamp))
                    state.selectedDateText = formatted
                } else {
                    state.selectedDateText = null
                }
                updateCreateEnabled()
            }
            is CreateGameDayAction.OnGymClick -> {
                state.isGymDialogVisible = true
            }
            is CreateGameDayAction.OnGymSelected -> {
                state.isGymDialogVisible = false
                state.selectedGymId = action.gym?.id
                state.selectedGymName = action.gym?.name ?: action.gym?.fullName
                updateCreateEnabled()
            }
            is CreateGameDayAction.OnDismissDialogs -> {
                state.isDateDialogVisible = false
                state.isGymDialogVisible = false
            }
            is CreateGameDayAction.OnCreateClick -> {
                createGameDay()
            }
        }
    }

    private fun updateCreateEnabled() {
        state.isCreateEnabled = state.selectedTimestamp != null && !state.selectedGymId.isNullOrEmpty()
    }

    private fun createGameDay() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            // TODO: Call repository to create game day if needed, or navigate back
            navigateAction.tryEmit(CreateGameDayNavigateAction.SuccessCreated)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }
}
