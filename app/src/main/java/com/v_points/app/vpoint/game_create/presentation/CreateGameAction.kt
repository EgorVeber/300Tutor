package com.v_points.app.vpoint.game_create.presentation

sealed interface CreateGameAction {
    data object OnNameClick : CreateGameAction
    data object OnScoreClick : CreateGameAction
    data class OnNameTextChanges(val text: String) : CreateGameAction
    object OnSaveNameClick : CreateGameAction
    data class OnCheckedChange(val userId: String, val checked: Boolean) : CreateGameAction
    data object OnPlayersOneTeamClick : CreateGameAction
    data object OnPlayersTwoTeamClick : CreateGameAction
    data object OnCreateClick : CreateGameAction
    data object OnDismissPlayers : CreateGameAction
    data object OnDismissNames : CreateGameAction
    data object OnDismissScore : CreateGameAction
    data object OnSavePlayersBottomClick : CreateGameAction
    data object OnScoreSaveClick : CreateGameAction
    data class OnScoreOneChange(val value: Int) : CreateGameAction
    data class OnScoreTwoChange(val value: Int) : CreateGameAction
}
