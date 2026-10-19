package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.EnterGroupModel
import com.v_points.app.domain.main.MainRepository
import javax.inject.Inject

class EnterGroupUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(key: String): EnterGroupModel =
        mainRepository.enterGroup(key = key)
}
