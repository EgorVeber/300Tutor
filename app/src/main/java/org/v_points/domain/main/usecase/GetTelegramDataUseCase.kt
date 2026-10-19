package org.v_points.domain.main.usecase

import org.v_points.domain.main.MainRepository
import org.v_points.domain.main.models.TelegramDataModel
import javax.inject.Inject

class GetTelegramDataUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): TelegramDataModel = mainRepository.getTelegramData()
}
