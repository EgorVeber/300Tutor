package com.v_points.app.vpoint.admin_section

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.IOException

object JsonHelper {

    private val json = Json { ignoreUnknownKeys = true }

    fun parseGame(raw: String): GameData =
        json.decodeFromString<GameData>(raw)


    fun jsonGet(context: Context) {
        val jsonInfo = context?.readJsonFromAssets(
            fileName = "IMG_1322.json"
        ).toString()


        val model = parseGame(jsonInfo)

        Log.d(
            "JsonHelper",
            " $model"
        )

        Log.d(
            "onViewCreated",
            context?.readJsonFromAssets(
                fileName = "IMG_1322.json"
            ).toString()
        )
    }

    fun Context.readJsonFromAssets(fileName: String): String? {
        return try {
            assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}

@Serializable
data class GameData(
    val info: GameInfo,
    val stats: List<PlayerStat> = emptyList()
)

@Serializable
data class GameInfo(
    @SerialName("GameName") val gameName: String,
    @SerialName("TeamOne") val teamOne: Int,
    @SerialName("TeamTwo") val teamTwo: Int,
    @SerialName("LocationID") val locationId: Int,
    @SerialName("Time") val time: Long,
    @SerialName("Description") val description: String,
    @SerialName("IsStatsServe") val isStatsServe: Int,
    @SerialName("IsGameStats") val isGameStats: Int
)

@Serializable
data class PlayerStat(
    @SerialName("TeamName") val teamName: String,
    @SerialName("StartP") val startP: Int,
    @SerialName("EndP") val endP: Int,
    @SerialName("UserName") val userName: String,
    @SerialName("Out") val out: Int,
    @SerialName("Net") val net: Int,
    @SerialName("Undershoot") val undershoot: Int,
    @SerialName("Clean") val clean: Int,
    @SerialName("Grace") val grace: Int,
    @SerialName("Ace") val ace: Int,
    @SerialName("NotReception") val notReception: Int,
    @SerialName("TransitionBall") val transitionBall: Int,
    @SerialName("TotalLabel") val totalLabel: String,
    @SerialName("SuccessServe") val successServe: Int,
    @SerialName("ErrorServe") val errorServe: Int
)

private fun getInitUser(): List<Triple<String, String, String>> {
    return listOf(
        Triple("Егор", "Вебер", "v_admin"),
        Triple("Вв", "Г", "v_user"),
        Triple("Евгений", "Строгонов", "v_user"),
        Triple("Максим", "Коровин", "v_user"),
        Triple("Юлия", "Чермарева", "v_user"),
        Triple("Вячеслав", "Сафронов", "v_user"),
        Triple("Светлана", "Му", "v_user"),
        Triple("Иван", "Архипов", "v_user"),
        Triple("Дмитрий", "Картавенко", "v_user"),
        Triple("Екатерина", "Новикова", "v_user"),
        Triple("Дарья", "Столбова", "v_user"),
        Triple("Олеся", "Волкова", "v_user"),
        Triple("Саша", "Гагарин", "v_user"),
        Triple("Максим", "Панаскин", "v_guest"),
        Triple("Макс", "Кречетов", "v_guest"),
    )
}

private fun getRoles(): List<String> {
    return listOf(
        "v_admin",
        "v_user",
        "v_guest",
    )
}

private fun getStats(): List<String> {
    return listOf(
        "Подача",//Serve
        "Прием",//Reception
        "Атака",//Attack
        "Пас",//Set
        "Защита",//Defense
    )
}

suspend fun migrateServeStats(firestore: FirebaseFirestore) {
    println("Начинаем миграцию serveStats...")

    // 1. Получаем все игровые дни
    val gameDays = firestore.collection("gameDays").get().await().documents

    for (dayDoc in gameDays) {
        val gameDayId = dayDoc.id

        // 2. Получаем все игры для этого дня
        val games = dayDoc.reference.collection("games").get().await().documents

        for (gameDoc in games) {
            val gameId = gameDoc.id

            // 3. Получаем все serveStats для этой игры
            val serveStats = gameDoc.reference.collection("serveStats").get().await().documents

            for (statDoc in serveStats) {
                val data = statDoc.data

                // Проверяем, нужно ли обновлять (если нет gameId)
                if (data?.get("gameId") == null || data["gameDayId"] == null) {

                    // Формируем данные для обновления
                    val updates = mapOf(
                        "gameId" to gameId,
                        "gameDayId" to gameDayId,
                        "transitionBall" to 0 // Добавляем новое поле
                    )

                    // Обновляем документ
                    statDoc.reference.update(updates).await()
                    println("Обновлен serveStat ${statDoc.id} (Игра: $gameId, День: $gameDayId)")
                }
            }
        }
    }
    println("Миграция завершена!")
}