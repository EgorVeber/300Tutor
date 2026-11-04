package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.GymResponse
import com.v_points.app.vpoint.fiersote.data.mappers.toGameModel
import com.v_points.app.vpoint.fiersote.data.mappers.toGymModel
import com.v_points.app.vpoint.fiersote.domain.GymModel
import javax.inject.Inject

class GymsRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getGyms(): List<GymModel> = dataSource.getGyms().mapNotNull { doc ->
        doc.toObject(GymResponse::class.java)?.toGymModel()
    }
}