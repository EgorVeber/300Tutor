package com.v_points.app.vpoint.participants.data

import android.util.Log
import com.v_points.app.vpoint.fiersote.FirestoreDataSource
import javax.inject.Inject
import kotlin.collections.mapNotNull

class ParticipantsRepository @Inject constructor(private val dataSource: FirestoreDataSource) {
    suspend fun getUsers(): List<UserFirestoreModel> {
        val docItems = dataSource.getUsers()
        Log.d("getUsers", docItems.toString())
        docItems.map {
            Log.d("getUsers", it.toString())
        }

        val mapNotNull = docItems.mapNotNull { doc ->
            doc.toObject(UserFirestoreModel::class.java)?.copy(id = doc.id)
        }
        val mapNotNullV2 = docItems.mapNotNull { doc ->
            doc.toObject(UserFirestoreModel::class.java)?.copy(id = doc.id)
        }
        Log.d("getUsers", mapNotNull.toString())
        mapNotNull.map {
            Log.d("getUsers", it.toString())
        }

        return mapNotNull
    }
}