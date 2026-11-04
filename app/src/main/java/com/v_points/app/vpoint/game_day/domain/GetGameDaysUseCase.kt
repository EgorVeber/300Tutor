package com.v_points.app.vpoint.game_day.domain

import com.v_points.app.vpoint.fiersote.data.repository.GameDaysRepository
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.fiersote.domain.GameDayStatModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import javax.inject.Inject
import kotlin.text.get

class GetGameDaysUseCase @Inject constructor(
    private val repo: GameDaysRepository
) {
    suspend operator fun invoke(userId: String): List<GameDayStatModel> {
        val days: List<GameDayModel> = repo.getAllGameDays()
        val games: List<GameModel> = repo.getAllGames()
        val gamesByDayId: Map<String, List<GameModel>> = games.groupBy { game -> game.gameDayId }
        return days.map { day ->
            day.toGameDayStatModel(gamesByDayId, userId)
        }
    }
}

private fun GameDayModel.toGameDayStatModel(
    gamesByDayId: Map<String, List<GameModel>>,
    userId: String
): GameDayStatModel {
    val gamesForDay = gamesByDayId[id].orEmpty()
    return GameDayStatModel(
        id = id,
        date = date,
        locationId = locationId,
        gamesCount = gamesForDay.size,
        isUserParticipated = gamesForDay.any { game -> game.allParticipantsIds.contains(userId) }
    )
}