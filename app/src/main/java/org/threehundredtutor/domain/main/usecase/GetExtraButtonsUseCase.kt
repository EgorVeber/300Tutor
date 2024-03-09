package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import javax.inject.Inject

class GetExtraButtonsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<ExtraButtonInfoModel> =
        mainRepository.getExtraButtons()
}
