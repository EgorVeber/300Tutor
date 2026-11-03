package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.StatResponse
import javax.inject.Inject

class StatsRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getStats(): List<StatResponse> {
        return dataSource.getStats().mapNotNull { doc ->
            doc.toObject(StatResponse::class.java)
        }
    }
}