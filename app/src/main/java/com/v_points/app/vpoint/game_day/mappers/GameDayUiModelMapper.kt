package com.v_points.app.vpoint.game_day.mappers

import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.vpoint.fiersote.domain.GameDayStatModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun GameDayStatModel.toGameDayUiModel(gymsMap: Map<String, String>): GameDayUiModel {
    // Форматируем дату (предполагаем, что date в секундах, умножаем на 1000)
    val dateString = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        .format(Date(date * 1000))

    return GameDayUiModel(
        id = id,
        formattedDate = dateString,
        gymName = gymsMap[locationId] ?: "Зал не указан",
        gamesCount = gamesCount,
        isUserParticipated = isUserParticipated
    )
}