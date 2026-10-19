package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.main.models.ExtraButtonInfoModel
import javax.inject.Inject

class GetExtraButtonsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<ExtraButtonInfoModel> =
        mainRepository.getExtraButtons()
}
