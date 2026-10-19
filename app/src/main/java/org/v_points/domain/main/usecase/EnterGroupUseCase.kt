package org.v_points.domain.main.usecase

import org.v_points.domain.main.EnterGroupModel
import org.v_points.domain.main.MainRepository
import javax.inject.Inject

class EnterGroupUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(key: String): EnterGroupModel =
        mainRepository.enterGroup(key = key)
}
