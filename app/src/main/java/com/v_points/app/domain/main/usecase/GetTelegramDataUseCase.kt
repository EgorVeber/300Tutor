package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.main.models.TelegramDataModel
import javax.inject.Inject

class GetTelegramDataUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): TelegramDataModel = mainRepository.getTelegramData()
}
