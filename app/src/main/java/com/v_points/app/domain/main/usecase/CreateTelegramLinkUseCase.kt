package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.main.models.CreateLinkTelegramModel
import javax.inject.Inject

class CreateTelegramLinkUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): CreateLinkTelegramModel = mainRepository.createLinkTelegram()
}
