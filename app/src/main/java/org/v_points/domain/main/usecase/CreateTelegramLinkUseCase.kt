package org.v_points.domain.main.usecase

import org.v_points.domain.main.MainRepository
import org.v_points.domain.main.models.CreateLinkTelegramModel
import javax.inject.Inject

class CreateTelegramLinkUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): CreateLinkTelegramModel = mainRepository.createLinkTelegram()
}
