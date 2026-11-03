package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import javax.inject.Inject

class RolesRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getRoles(): List<RoleResponse> {
        return dataSource.getRoles().mapNotNull { doc ->
            doc.toObject(RoleResponse::class.java)
        }
    }
}