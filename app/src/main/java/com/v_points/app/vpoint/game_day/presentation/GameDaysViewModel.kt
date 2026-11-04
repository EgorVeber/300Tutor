package com.v_points.app.vpoint.game_day.presentation

import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.vpoint.fiersote.data.repository.GymsRepository
import com.v_points.app.vpoint.fiersote.domain.GameDayStatModel
import com.v_points.app.vpoint.game_day.MainState
import com.v_points.app.vpoint.game_day.MutableMainState
import com.v_points.app.vpoint.game_day.domain.GetGameDaysUseCase
import com.v_points.app.vpoint.game_day.mappers.toGameDayUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GameDaysViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getGameDaysUseCase: GetGameDaysUseCase,
    private val gymsRepository: GymsRepository,
) : BaseViewModel() {

    private val screenGameDayState = MutableMainState()
    private val loadingState = MutableStateFlow(false)

    private val navigateAction = SingleSharedFlow<MainScreenNavigateAction>()
    fun getNavigateAction(): Flow<MainScreenNavigateAction> = navigateAction

    fun getScreenGameDayState(): MainState = screenGameDayState

    fun getLoadingState() = loadingState.asStateFlow()

    init {
        loadListData()
    }

    private val userId = "YdjFrfak3zSt7NsOGgZr"

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val gameDays: List<GameDayStatModel> = getGameDaysUseCase(userId)
            val gyms: Map<String, String> = gymsRepository.getGyms().associate { it.id to it.name }
            screenGameDayState.items = gameDays.map { gameDayStat ->
                gameDayStat.toGameDayUiModel(gyms)
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnGameDayClick -> onGameDayClick(action.item)
        }
    }

    private fun onGameDayClick(item: GameDayUiModel) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            navigateAction.tryEmit(MainScreenNavigateAction.GamesScreen(item.id))
        }, catchBlock = { throwable ->
            throwable.printStackTrace()
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }
}