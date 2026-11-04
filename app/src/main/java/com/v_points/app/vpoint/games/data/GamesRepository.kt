package com.v_points.app.vpoint.games.data

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.mappers.toGameModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getAllGames(): List<GameModel> {
        return dataSource.getAllGames()
            .mapNotNull { doc -> doc.toObject(GameResponse::class.java) }
            .mapNotNull { it.toGameModel() }
    }
}
