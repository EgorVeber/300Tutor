package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.GameDayResponse
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.models.UserStatResponse
import com.v_points.app.vpoint.fiersote.data.mappers.toGameDayModel
import com.v_points.app.vpoint.fiersote.data.mappers.toUserStatModel
import com.v_points.app.vpoint.fiersote.data.mappers.toGameModel
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.fiersote.domain.UserStatModel
import javax.inject.Inject

class GameDaysRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getAllGameDays(): List<GameDayModel> = dataSource.getGameDays()
        .mapNotNull { doc ->
            doc.toObject(GameDayResponse::class.java)?.toGameDayModel()
        }

    suspend fun getAllGames(): List<GameModel> = dataSource.getAllGames()
        .mapNotNull { doc ->
            doc.toObject(GameResponse::class.java)?.toGameModel()
        }

    suspend fun getAllUserStats(): List<UserStatModel> = dataSource.getAllUserStats()
        .mapNotNull { doc ->
            doc.toObject(UserStatResponse::class.java)?.toUserStatModel()
        }
}