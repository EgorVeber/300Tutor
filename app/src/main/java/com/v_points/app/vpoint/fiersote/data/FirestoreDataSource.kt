package com.v_points.app.vpoint.fiersote.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getUsers(): List<DocumentSnapshot> =
        firebaseFirestore.collection(USERS_COLLECTION).get().await().documents

    suspend fun getRoles(): List<DocumentSnapshot> =
        firebaseFirestore.collection(ROLES_COLLECTION).get().await().documents

    suspend fun getStats(): List<DocumentSnapshot> =
        firebaseFirestore.collection(STATS_COLLECTION).get().await().documents

    suspend fun getGyms(): List<DocumentSnapshot> =
        firebaseFirestore.collection(GYMS_COLLECTION).get().await().documents

    suspend fun getGameDays(): List<DocumentSnapshot> =
        firebaseFirestore.collection(GAME_DAYS_COLLECTION).get().await().documents

    suspend fun getAllGames(): List<DocumentSnapshot> =
        firebaseFirestore.collectionGroup(GAMES_COLLECTION).get().await().documents

    suspend fun getGamesForDay(dayId: String): List<DocumentSnapshot> =
        firebaseFirestore.collection(GAME_DAYS_COLLECTION).document(dayId)
            .collection(GAMES_COLLECTION).get().await().documents

    suspend fun getAllUserStats(): List<DocumentSnapshot> =
        firebaseFirestore.collectionGroup(USER_STATS_COLLECTION).get().await().documents

    companion object {
        const val USERS_COLLECTION = "users"
        const val ROLES_COLLECTION = "roles"
        const val STATS_COLLECTION = "stats"
        const val GYMS_COLLECTION = "gyms"


        const val GAME_DAYS_COLLECTION = "gameDays"
        const val GAMES_COLLECTION = "games"
        const val USER_STATS_COLLECTION = "userStats"
    }
}