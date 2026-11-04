package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.mappers.toStatModel
import com.v_points.app.vpoint.fiersote.data.models.StatResponse
import com.v_points.app.vpoint.fiersote.domain.StatModel
import javax.inject.Inject

class StatsRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getStats(): List<StatModel> = dataSource.getStats().mapNotNull { doc ->
        doc.toObject(StatResponse::class.java)?.toStatModel()
    }
}