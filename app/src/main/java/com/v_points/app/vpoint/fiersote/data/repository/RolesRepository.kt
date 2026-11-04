package com.v_points.app.vpoint.fiersote.data.repository

import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.mappers.toRoleModel
import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import com.v_points.app.vpoint.fiersote.domain.RoleModel
import javax.inject.Inject

class RolesRepository @Inject constructor(
    private val dataSource: FirestoreDataSource
) {
    suspend fun getRoles(): List<RoleModel> =
        dataSource.getRoles().mapNotNull { doc ->
            doc.toObject(RoleResponse::class.java)?.toRoleModel()
        }
}