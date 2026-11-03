package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.GameDayResponse
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.models.UserStatResponse
import com.v_points.app.vpoint.fiersote.data.toModelOrNull
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.fiersote.domain.UserStatModel
import javax.inject.Inject

class GameDaysRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {

    suspend fun getGameDaysWithDetails(currentUserId: String): List<GameDayModel> {
        // 1. Получаем дни
        val days = dataSource.getGameDays()
            .mapNotNull { doc -> doc.toObject(GameDayResponse::class.java) }
            .mapNotNull { it.toModelOrNull() }

        // 2. Получаем ВСЕ игры (теперь они знают свой dayId)
        val allGames = dataSource.getAllGames()
            .mapNotNull { doc -> doc.toObject(GameResponse::class.java) }
            .mapNotNull { it.toModelOrNull() }

        // 3. Группируем игры по gameDayId
        val gamesByDayId = allGames.groupBy { it.gameDayId }

        // 4. Обогащаем дни
        return days.map { day ->
            val gamesForDay = gamesByDayId[day.id] ?: emptyList()

            day.copy(
                gamesCount = gamesForDay.size,
                isUserParticipated = gamesForDay.any {
                    it.allParticipantsIds.contains(currentUserId)
                }
            )
        }
    }

    suspend fun getAllGames(): List<GameModel> {
        return dataSource.getAllGames()
            .mapNotNull { doc -> doc.toObject(GameResponse::class.java) }
            .mapNotNull { it.toModelOrNull() }
    }

    suspend fun getGamesForDay(dayId: String): List<GameModel> {
        return dataSource.getGamesForDay(dayId)
            .mapNotNull { doc -> doc.toObject(GameResponse::class.java) }
            .mapNotNull { it.toModelOrNull() }
    }

    suspend fun getAllUserStats(): List<UserStatModel> {
        return dataSource.getAllUserStats()
            .mapNotNull { doc -> doc.toObject(UserStatResponse::class.java) }
            .mapNotNull { it.toModelOrNull() }
    }
}