package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.CreateLinkTelegramModel
import javax.inject.Inject

class CreateTelegramLinkUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): CreateLinkTelegramModel = mainRepository.createLinkTelegram()
}
