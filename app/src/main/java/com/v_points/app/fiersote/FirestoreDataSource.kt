package com.v_points.app.fiersote

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getUsers(): List<DocumentSnapshot> =
        firebaseFirestore.collection(USERS_COLLECTION).get().await().documents

    companion object {
        const val USERS_COLLECTION = "v_users"
    }
}