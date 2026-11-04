package com.v_points.app.vpoint.fiersote.domain

data class UserStatModel(
    val id: String,
    val userId: String,
    val statId: String,
    val reviewerId: String,
    val value: Double
)

data class GameDayModel(
    val id: String,
    val date: Long,
    val locationId: String,
)

data class GameDayStatModel(
    val id: String,
    val date: Long,
    val locationId: String,
    val gamesCount: Int,
    val isUserParticipated: Boolean
)

data class GameModel(
    val id: String,
    val gameDayId: String,
    val name: String,
    val scoreTeamOne: Int,
    val scoreTeamTwo: Int,
    val participantsIdsTeamOne: List<String>,
    val participantsIdsTeamTwo: List<String>,
    val allParticipantsIds: List<String>
)