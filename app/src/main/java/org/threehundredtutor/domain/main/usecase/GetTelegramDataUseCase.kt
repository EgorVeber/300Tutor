package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.TelegramDataModel
import javax.inject.Inject

class GetTelegramDataUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): TelegramDataModel = mainRepository.getTelegramData()
}
