package com.v_points.app.vpoint.games.domain

import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.games.data.GamesRepository
import jakarta.inject.Inject

class GetGamesForDayUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(dayId: String): List<GameModel> =
        gamesRepository.getGamesForDay(dayId)
}