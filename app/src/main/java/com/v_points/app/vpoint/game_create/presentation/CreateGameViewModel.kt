package com.v_points.app.vpoint.game_create.presentation

import androidx.lifecycle.viewModelScope
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.SnackBarType
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.game_create.di.CreateGameComponentModel
import com.v_points.app.vpoint.games.domain.AddGameToDayUseCase
import com.v_points.app.vpoint.participants.domain.GetParticipantsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateGameViewModel @Inject constructor(
    private val componentModel: CreateGameComponentModel,
    private val resourceProvider: ResourceProvider,
    private val getParticipantsUseCase: GetParticipantsUseCase,
    private val addGameToDayUseCase: AddGameToDayUseCase
) : BaseViewModel() {

    private val state = MutableCreateGameState()
    private val navigateAction = SingleSharedFlow<CreateGameNavigateAction>()

    private var cacheUsers: List<PlayerUiModel> = listOf()

    val gameDayId: String get() = componentModel.gameDayId

    fun getState(): CreateGameState = state
    fun getNavigateAction(): Flow<CreateGameNavigateAction> = navigateAction

    init {
        viewModelScope.launchJob(tryBlock = {
            val users: List<PlayerUiModel> = getParticipantsUseCase().map { userModel ->
                PlayerUiModel(
                    id = userModel.id,
                    name = "${userModel.firstName} ${userModel.lastName.take(1)}",
                    teamId = null,
                    enabled = true,
                    checked = false
                )
            }

            if (componentModel.one.isNotEmpty() && componentModel.two.isNotEmpty()) {
                val listOne = componentModel.one
                val listTwo = componentModel.two
                val userGames = users.map {
                    when (it.id) {
                        in listOne -> {
                            it.copy(teamId = 1, checked = true)
                        }

                        in listTwo -> {
                            it.copy(teamId = 2, checked = true)
                        }

                        else -> {
                            it
                        }
                    }
                }
                cacheUsers = userGames
                state.players = users
                state.playersOneTeamTitle =
                    cacheUsers.filter { it.teamId == 1 }.joinToString(", ") { it.name.toString() }
                state.playersTwoTeamTitle =
                    cacheUsers.filter { it.teamId == 2 }.joinToString(", ") { it.name.toString() }
            } else {
                cacheUsers = users
                state.players = users
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    var currentDialog: Int? = null

    fun onAction(action: CreateGameAction) {
        when (action) {
            is CreateGameAction.OnNameClick -> {
                state.showNameBottom = true
                createButtonEnabled()
            }

            is CreateGameAction.OnCreateClick -> {
                createGame()
            }

            CreateGameAction.OnDismissPlayers -> {
                state.showPlayersBottom = false
            }

            is CreateGameAction.OnSavePlayersBottomClick -> onSavePlayersBottomClick()
            CreateGameAction.OnPlayersOneTeamClick -> {
                currentDialog = 1
                state.players = cacheUsers.map {
                    when (it.teamId) {
                        2 -> {
                            it.copy(enabled = false)
                        }

                        1 -> it.copy(enabled = true)
                        else -> {
                            it
                        }
                    }
                }.filter { it.teamId != 2 }.sortedByDescending { it.teamId == 1 }
                state.playersBottomTitle = "Выберите игроков К1"
                state.showPlayersBottom = true
            }

            CreateGameAction.OnPlayersTwoTeamClick -> {
                currentDialog = 2
                state.players = cacheUsers.map {
                    when (it.teamId) {
                        1 -> {
                            it.copy(enabled = false)
                        }

                        2 -> it.copy(enabled = true)
                        else -> {
                            it
                        }
                    }
                }.filter { it.teamId != 1 }.sortedByDescending { it.teamId == 2 }
                state.playersBottomTitle = "Выберите игроков К2"
                state.showPlayersBottom = true
            }

            CreateGameAction.OnScoreClick -> {
                state.showScoreBottom = true
            }

            CreateGameAction.OnDismissScore -> {
                state.showScoreBottom = false
            }

            is CreateGameAction.OnCheckedChange -> {
                if (currentDialog == 1) {
                    val countPLayers = state.players.count { it.teamId == 1 }
                    if (countPLayers == 6 && action.checked) return
                } else if (currentDialog == 2) {
                    val countPLayers = state.players.count { it.teamId == 2 }
                    if (countPLayers == 6 && action.checked) return
                }
                state.players = state.players.map {
                    if (it.id == action.userId) {
                        it.copy(
                            checked = !it.checked,
                            teamId = if (it.checked) null else currentDialog
                        )
                    } else {
                        it
                    }
                }
            }

            is CreateGameAction.OnSaveNameClick -> {
                state.gameNameCache = state.gameName
                state.showNameBottom = false
                createButtonEnabled()
            }

            CreateGameAction.OnDismissNames -> {
                state.showNameBottom = false
            }

            is CreateGameAction.OnNameTextChanges -> {
                state.gameName = action.text
            }

            CreateGameAction.OnScoreSaveClick -> {
                state.scoreTitle = "К1 ${state.scoreOneTeam}:${state.scoreTwoTeam} К2"
                state.isScoreEnabled = true
                state.showScoreBottom = false
                createButtonEnabled()
            }

            is CreateGameAction.OnScoreOneChange -> {
                state.scoreOneTeam = action.value
            }

            is CreateGameAction.OnScoreTwoChange -> {
                state.scoreTwoTeam = action.value
            }
        }
    }

    private fun onSavePlayersBottomClick() {
        cacheUsers = cacheUsers.map { cacheUser ->
            val user: PlayerUiModel? = state.players.find { cacheUser.id == it.id }
            if (user != null) {
                cacheUser.copy(teamId = user.teamId, checked = user.checked)
            } else {
                cacheUser
            }
        }
        if (currentDialog == 1) {
            state.playersOneTeamTitle =
                cacheUsers.filter { it.teamId == 1 }.joinToString(", ") { it.name.toString() }
        } else {
            state.playersTwoTeamTitle =
                cacheUsers.filter { it.teamId == 2 }.joinToString(", ") { it.name.toString() }
        }
        state.showPlayersBottom = false
        createButtonEnabled()
    }

    private fun createButtonEnabled() {
        state.isCreateEnabled =
            cacheUsers.count { it.teamId != null } > 2 && state.gameName.isNotBlank() && state.isScoreEnabled
    }

    private fun createGame() {
        viewModelScope.launchJob(tryBlock = {
            state.loading = true
            val gameModel = GameModel(
                id = "",
                gameDayId = gameDayId,
                name = state.gameName,
                scoreTeamOne = state.scoreOneTeam,
                scoreTeamTwo = state.scoreTwoTeam,
                participantsIdsTeamOne = cacheUsers.mapNotNull { if (it.teamId == 1) it.id else null },
                participantsIdsTeamTwo = cacheUsers.mapNotNull { if (it.teamId == 2) it.id else null },
                allParticipantsIds = cacheUsers.mapNotNull { if (it.teamId == 2 || it.teamId == 1) it.id else null }
            )
            val newId = addGameToDayUseCase(gameModel)
            navigateAction.tryEmit(
                CreateGameNavigateAction.ShowSnack(
                    "Игра успешно создана для дня: $newId",
                    SnackBarType.SUCCESS
                )
            )
            navigateAction.tryEmit(CreateGameNavigateAction.SuccessCreated)
            //  TODO  автообновление пред экрана
        }, catchBlock = { throwable ->
            handleError(throwable)
            navigateAction.tryEmit(
                CreateGameNavigateAction.ShowSnack(
                    throwable.localizedMessage ?: "Ошибка создания игры",
                    SnackBarType.ERROR
                )
            )
        }, finallyBlock = {
            state.loading = true
        })
    }
}
