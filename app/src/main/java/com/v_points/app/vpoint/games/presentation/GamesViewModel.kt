package com.v_points.app.vpoint.games.presentation

import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot
import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.components.participant.PlayerUiModel
import com.v_points.app.ui_core.components.participant.TeamParticipantsUiModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.fiersote.domain.UserModel
import com.v_points.app.vpoint.games.domain.GetGamesForDayUseCase
import com.v_points.app.vpoint.games.presentation.mappers.toGameUiModel
import com.v_points.app.vpoint.participants.domain.GetParticipantsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GamesViewModel @Inject constructor(
    private val gameDayId: String,
    private val resourceProvider: ResourceProvider,
    private val getGamesForDayUseCase: GetGamesForDayUseCase,
    private val getParticipantsUseCase: GetParticipantsUseCase,
) : BaseViewModel() {

    private val screenGameState = MutableGamesState()
    private val loadingState = MutableStateFlow(false)

    fun getScreenGameState(): MutableGamesState = screenGameState
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        loadListData()
    }

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val gamesDeferred = async { getGamesForDayUseCase(gameDayId) }

            val usersDeferred =
                async { getParticipantsUseCase() } // Добавить кеш юзеров и всех справочников на старте

            val games = gamesDeferred.await()
            val game =
                games.firstOrNull() ?: return@launchJob // Пока берем участников из первой игры

            val users = usersDeferred.await()
            Snapshot.withMutableSnapshot {
                screenGameState.participantsBlock =
                    getTeamParticipantsUiModel(game, users)

                screenGameState.gamesBlock = games.mapIndexed { index, game ->
                    game.toGameUiModel(index)
                }
            }

        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    private fun getTeamParticipantsUiModel(
        game: GameModel,
        users: List<UserModel>
    ): TeamParticipantsUiModel {
        val usersMap =
            users.associate { user -> user.id to "${user.firstName} ${user.lastName.take(1)}" }
        Log.d("TAG", "getTeamParticipantsUiModel() called with: game = $game, users = $users")
        Log.d("TAG", ", usersMap = $usersMap")
        val currentUserId = "SEp15Ot5vihizaL7zPSB"
        return TeamParticipantsUiModel(
            teamOneName = "Команда 1",
            teamTwoName = "Команда 2",
            teamOnePlayers = game.participantsIdsTeamOne.map { user ->
                PlayerUiModel(
                    name = usersMap[user] ?: user,
                    rating = 0, // Тянуть рейтинг на момент игры а не текущий,
                    isCurrentUser = user == currentUserId
                )
            },
            teamTwoPlayers = game.participantsIdsTeamTwo.map { user ->
                PlayerUiModel(
                    name = usersMap[user] ?: "Unknown",
                    rating = 0, // Тянуть рейтинг на момент игры а не текущий
                    isCurrentUser = user == currentUserId
                )
            }
        )
    }
}
