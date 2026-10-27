package com.v_points.app.vpoint.fiersote

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.vpoint.participants.data.UserFirestoreModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getUsers(): List<DocumentSnapshot> =
        firebaseFirestore.collection(USERS_COLLECTION).get().await().documents

    suspend fun getUsersV2(): List<UserFirestoreModel> =
        firebaseFirestore
            .collection("v_users")
            .get().await()
            .documents
            .mapNotNull { doc ->
                doc.toObject(UserFirestoreModel::class.java)?.copy(id = doc.id)
            }

    companion object {
        const val USERS_COLLECTION = "v_users"
    }
}