package com.v_points.app.vpoint.games.data

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.mappers.toServeStatModel
import com.v_points.app.vpoint.fiersote.data.models.ServeStatResponse
import com.v_points.app.vpoint.fiersote.domain.ServeStatModel
import javax.inject.Inject

class ServeStatsRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getServeStatsForDay(gameDayId: String): List<ServeStatModel> =
        dataSource.getServeStatsByGameDay(gameDayId).mapNotNull { doc ->
            doc.toObject(ServeStatResponse::class.java)?.toServeStatModel()
        }

    suspend fun getAllServeStats(): List<ServeStatModel> =
        dataSource.getAllServeStats().mapNotNull { doc ->
            doc.toObject(ServeStatResponse::class.java)?.toServeStatModel()
        }
}