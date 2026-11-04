package com.v_points.app.vpoint.games.domain

import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.games.data.GamesRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(): List<GameModel> = gamesRepository.getAllGames()
}