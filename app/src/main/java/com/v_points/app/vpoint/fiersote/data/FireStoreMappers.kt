package com.v_points.app.vpoint.fiersote.data

import com.v_points.app.vpoint.fiersote.data.models.GameDayResponse
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.models.GymResponse
import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import com.v_points.app.vpoint.fiersote.data.models.StatResponse
import com.v_points.app.vpoint.fiersote.data.models.UserResponse
import com.v_points.app.vpoint.fiersote.data.models.UserStatResponse
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.fiersote.domain.GymModel
import com.v_points.app.vpoint.fiersote.domain.RoleModel
import com.v_points.app.vpoint.fiersote.domain.StatModel
import com.v_points.app.vpoint.fiersote.domain.UserModel
import com.v_points.app.vpoint.fiersote.domain.UserStatModel

// --- User ---
fun UserResponse.toModelOrNull(): UserModel? {
    return UserModel(
        id = id ?: return null,
        firstName = firstName ?: return null,
        lastName = lastName ?: "",
        roleId = roleId ?: return null
    )
}

// --- Role ---
fun RoleResponse.toModelOrNull(): RoleModel? {
    return RoleModel(
        id = id ?: return null,
        roleType = roleType ?: return null
    )
}

// --- Stat ---
fun StatResponse.toModelOrNull(): StatModel? {
    return StatModel(
        id = id ?: return null,
        statName = statName ?: return null,
        weight = weight ?: 0.0
    )
}

// --- Gym ---
fun GymResponse.toModelOrNull(): GymModel? {
    return GymModel(
        id = id ?: return null,
        name = name ?: return null,
        fullName = fullName ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
    )
}

// --- GameDay ---
fun GameDayResponse.toModelOrNull(): GameDayModel? {
    return GameDayModel(
        id = id ?: return null,
        date = date ?: return null,
        locationId = locationId ?: return null
    )
}

// --- Game ---
fun GameResponse.toModelOrNull(): GameModel? {
    return GameModel(
        id = id ?: return null,
        gameDayId = gameDayId ?: return null, // Если дня нет — игра битая
        name = name ?: return null,
        scoreTeamOne = scoreTeamOne ?: 0,
        scoreTeamTwo = scoreTeamTwo ?: 0,
        participantsIdsTeamOne = participantsIdsTeamOne ?: emptyList(),
        participantsIdsTeamTwo = participantsIdsTeamTwo ?: emptyList(),
        allParticipantsIds = allParticipantsIds ?: emptyList()
    )
}

// --- UserStat ---
fun UserStatResponse.toModelOrNull(): UserStatModel? {
    return UserStatModel(
        id = id ?: return null,
        userId = userId ?: return null,
        statId = statId ?: return null,
        reviewerId = reviewerId ?: return null,
        value = value ?: 0.0
    )
}