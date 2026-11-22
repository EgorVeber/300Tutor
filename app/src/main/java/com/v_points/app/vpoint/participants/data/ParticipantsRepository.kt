package com.v_points.app.vpoint.participants.data

import android.util.Log
import com.v_points.app.vpoint.fiersote.data.FirestoreDataSource
import com.v_points.app.vpoint.fiersote.data.mappers.toRoleModel
import com.v_points.app.vpoint.fiersote.data.mappers.toUserModel
import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import com.v_points.app.vpoint.fiersote.data.models.UserResponse
import com.v_points.app.vpoint.fiersote.domain.RoleModel
import com.v_points.app.vpoint.fiersote.domain.UserModel
import javax.inject.Inject
import kotlin.collections.mapNotNull

class ParticipantsRepository @Inject constructor(private val dataSource: FirestoreDataSource) {
    suspend fun getUsers(): List<UserModel> {
        val roles: List<RoleModel> = dataSource.getRoles().mapNotNull { doc ->
            doc.toObject(RoleResponse::class.java)?.toRoleModel()
        }
        val users =  dataSource.getUsers().mapNotNull { doc ->
            doc.toObject(UserResponse::class.java)?.toUserModel(roles)
        }
        Log.d("getUsers", roles.toString())
        Log.d("getUsers", users.toString())
        return users
    }
}