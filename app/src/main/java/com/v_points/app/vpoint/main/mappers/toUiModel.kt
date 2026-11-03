
package com.v_points.app.vpoint.main.mappers

import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Превращает доменную модель в UI-модель.
 * @param gymsMap Мапа: ID зала -> Имя зала (чтобы подставить имя по locationId).
 */
fun GameDayModel.toUiModel(gymsMap: Map<String, String>): GameDayUiModel {
    // Форматируем дату (предполагаем, что date в секундах, умножаем на 1000)
    val dateString = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        .format(Date(this.date * 1000))

    return GameDayUiModel(
        id = this.id,
        formattedDate = dateString,
        gymName = gymsMap[this.locationId] ?: "Зал не указан",
        gamesCount = this.gamesCount, // Берем из доменной модели
        isUserParticipated = this.isUserParticipated // Берем из доменной модели
    )
}