package com.v_points.app.vpoint.games.presentation

import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.vpoint.games.domain.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GamesViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getGamesUseCase: GetGamesUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableGamesState()
    private val loadingState = MutableStateFlow(false)

    fun getMainState(): MutableGamesState = uiItemsState
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        loadListData()
    }

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val games = getGamesUseCase()
            uiItemsState.games = games.map { game ->
                GameUiModel(
                    id = game.id,
                    name = game.name.ifEmpty { "Игра #${game.id.take(4)}" },
                    scoreText = "${game.scoreTeamOne} : ${game.scoreTeamTwo}",
                    teamOneCount = game.participantsIdsTeamOne.size,
                    teamTwoCount = game.participantsIdsTeamTwo.size
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }
}
