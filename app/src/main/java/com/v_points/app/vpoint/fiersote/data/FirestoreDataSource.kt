package com.v_points.app.vpoint.fiersote.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.vpoint.fiersote.data.FirestoreFields.F_GAME_DAY_ID
import com.v_points.app.vpoint.fiersote.data.models.GameDayRequest
import com.v_points.app.vpoint.fiersote.domain.GameModel
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

    suspend fun addGameDay(gameDay: GameDayRequest): String =
        firebaseFirestore
            .collection(GAME_DAYS_COLLECTION)
            .add(gameDay)
            .await()
            .id

    suspend fun addGameToDay(game: GameModel): String {
        return firebaseFirestore
            .collection(GAME_DAYS_COLLECTION)
            .document(game.gameDayId)
            .collection(GAMES_COLLECTION)
            .add(game)
            .await()
            .id
    }

    suspend fun addGamesToDay(games: List<GameModel>): List<String> {
        if (games.isEmpty()) return emptyList()

        val batch = firebaseFirestore.batch()
        val generatedIds = mutableListOf<String>()

        for (game in games) {
            // 1. Создаем ссылку на документ (Firestore генерирует ID локально)
            val gameRef = firebaseFirestore
                .collection(GAME_DAYS_COLLECTION)
                .document(game.gameDayId)
                .collection(GAMES_COLLECTION)
                .document()

            // 2. Сохраняем в батч саму модель (без id)
            batch.set(gameRef, game)

            // 3. Запоминаем ID, чтобы вернуть его в UI
            generatedIds.add(gameRef.id)
        }

        batch.commit().await()
        return generatedIds
    }

    suspend fun getServeStatsByGameDay(gameDayId: String): List<DocumentSnapshot> =
        firebaseFirestore.collectionGroup(SERVE_STATS_COLLECTION)
            .whereEqualTo(F_GAME_DAY_ID, gameDayId)
            .get()
            .await()
            .documents

    suspend fun getAllServeStats(): List<DocumentSnapshot> =
        firebaseFirestore.collectionGroup(SERVE_STATS_COLLECTION).get().await().documents

    companion object {
        const val USERS_COLLECTION = "users"
        const val ROLES_COLLECTION = "roles"
        const val STATS_COLLECTION = "stats"
        const val GYMS_COLLECTION = "gyms"
        const val SERVE_STATS_COLLECTION = "serveStats"
        const val GAME_DAYS_COLLECTION = "gameDays"
        const val GAMES_COLLECTION = "games"
        const val USER_STATS_COLLECTION = "userStats"
    }
}