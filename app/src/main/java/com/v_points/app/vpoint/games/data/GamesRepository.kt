package com.v_points.app.vpoint.games.data

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.mappers.toGameModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getAllGames(): List<GameModel> = dataSource.getAllGames()
        .mapNotNull { doc -> doc.toObject(GameResponse::class.java)?.toGameModel() }

    suspend fun getGamesForDay(dayId: String): List<GameModel> = dataSource.getGamesForDay(dayId)
        .mapNotNull { doc ->
            doc.toObject(GameResponse::class.java)?.toGameModel()
        }
}
