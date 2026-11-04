package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class GameDayResponse(
    @DocumentId val id: String? = null,
    val date: Long? = null,
    val locationId: String? = null
)

data class GameResponse(
    @DocumentId val id: String? = null,
    val gameDayId: String? = null,
    val name: String? = null,
    val scoreTeamOne: Int? = null,
    val scoreTeamTwo: Int? = null,
    val participantsIdsTeamOne: List<String>? = null,
    val participantsIdsTeamTwo: List<String>? = null,
    val allParticipantsIds: List<String>? = null
)

data class UserStatResponse(
    @DocumentId val id: String? = null,
    val userId: String? = null,
    val statId: String? = null,
    val reviewerId: String? = null,
    val value: Double? = null
)