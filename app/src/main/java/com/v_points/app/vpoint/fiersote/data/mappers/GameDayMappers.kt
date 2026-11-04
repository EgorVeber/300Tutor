package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.GameDayResponse
import com.v_points.app.vpoint.fiersote.data.models.GameResponse
import com.v_points.app.vpoint.fiersote.data.models.UserStatResponse
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.fiersote.domain.GameModel
import com.v_points.app.vpoint.fiersote.domain.UserStatModel

fun GameDayResponse.toGameDayModel(): GameDayModel {
    return GameDayModel(
        id = id ?: error("GameDayResponse id null"),
        date = date ?: error("GameDayResponse date null"),
        locationId = locationId ?: error("GameDayResponse locationId null")
    )
}

fun GameResponse.toGameModel(): GameModel {
    return GameModel(
        id = id ?: error("GameResponse id null"),
        gameDayId = gameDayId ?: error("GameResponse gameDayId null"),
        name = name ?: error("GameResponse name null"),
        scoreTeamOne = scoreTeamOne ?: error("GameResponse scoreTeamOne null"),
        scoreTeamTwo = scoreTeamTwo ?: error("GameResponse scoreTeamTwo null"),
        participantsIdsTeamOne = participantsIdsTeamOne.orEmpty(),
        participantsIdsTeamTwo = participantsIdsTeamTwo.orEmpty(),
        allParticipantsIds = allParticipantsIds.orEmpty()
    )
}

fun UserStatResponse.toUserStatModel(): UserStatModel {
    return UserStatModel(
        id = id ?: error("UserStatResponse id null"),
        userId = userId ?: error("UserStatResponse userId null"),
        statId = statId ?: error("UserStatResponse statId null"),
        reviewerId = reviewerId ?: error("UserStatResponse reviewerId null"),
        value = value ?: error("UserStatResponse value null")
    )
}