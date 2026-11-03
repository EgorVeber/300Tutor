package com.v_points.app.vpoint.fiersote.domain

data class UserModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val roleId: String
)

data class RoleModel(
    val id: String,
    val roleType: String
)

data class StatModel(
    val id: String,
    val statName: String,
    val weight: Double
)

data class GymModel(
    val id: String,
    val name: String,
    val fullName: String,
    val latitude: Double,
    val longitude: Double
)

data class GameDayModel(
    val id: String,
    val date: Long,
    val locationId: String,
    // Вычисляемые поля (не хранятся в Firestore напрямую)
    val gamesCount: Int = 0,
    val isUserParticipated: Boolean = false
)

data class GameModel(
    val id: String,
    val gameDayId: String, // <-- НОВОЕ ПОЛЕ
    val name: String,
    val scoreTeamOne: Int,
    val scoreTeamTwo: Int,
    val participantsIdsTeamOne: List<String>,
    val participantsIdsTeamTwo: List<String>,
    val allParticipantsIds: List<String>
)

data class UserStatModel(
    val id: String,
    val userId: String,
    val statId: String,
    val reviewerId: String,
    val value: Double
)