package org.v_points.domain.main.usecase

import org.v_points.domain.main.MainRepository
import org.v_points.domain.main.models.ExtraButtonInfoModel
import javax.inject.Inject

class GetExtraButtonsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<ExtraButtonInfoModel> =
        mainRepository.getExtraButtons()
}
