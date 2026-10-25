package com.v_points.app.admin_section

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object JsonHelper {

    private val json = Json { ignoreUnknownKeys = true }

    fun parseGame(raw: String): GameData =
        json.decodeFromString<GameData>(raw)
}

@Serializable
data class GameData(
    val info: GameInfo,
    val stats: List<PlayerStat> = emptyList()
)

@Serializable
data class GameInfo(
    @SerialName("GameName")   val gameName: String,
    @SerialName("TeamOne")    val teamOne: Int,
    @SerialName("TeamTwo")    val teamTwo: Int,
    @SerialName("LocationID") val locationId: Int,
    @SerialName("Time")       val time: Long,
    @SerialName("Description") val description: String,
    @SerialName("IsStatsServe") val isStatsServe: Int,
    @SerialName("IsGameStats")  val isGameStats: Int
)

@Serializable
data class PlayerStat(
    @SerialName("TeamName")       val teamName: String,
    @SerialName("StartP")         val startP: Int,
    @SerialName("EndP")           val endP: Int,
    @SerialName("UserName")       val userName: String,
    @SerialName("Out")            val out: Int,
    @SerialName("Net")            val net: Int,
    @SerialName("Undershoot")     val undershoot: Int,
    @SerialName("Clean")          val clean: Int,
    @SerialName("Grace")          val grace: Int,
    @SerialName("Ace")            val ace: Int,
    @SerialName("NotReception")   val notReception: Int,
    @SerialName("TransitionBall") val transitionBall: Int,
    @SerialName("TotalLabel")     val totalLabel: String,
    @SerialName("SuccessServe")   val successServe: Int,
    @SerialName("ErrorServe")     val errorServe: Int
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