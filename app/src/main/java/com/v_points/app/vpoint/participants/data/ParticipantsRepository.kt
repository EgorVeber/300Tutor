package com.v_points.app.vpoint.participants.data

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.UserResponse
import javax.inject.Inject
import kotlin.collections.mapNotNull

class ParticipantsRepository @Inject constructor(private val dataSource: FirestoreDataSource) {
    suspend fun getUsers(): List<UserResponse> {
        return dataSource.getUsers().mapNotNull { doc ->
            doc.toObject(UserResponse::class.java)
        }
    }
}