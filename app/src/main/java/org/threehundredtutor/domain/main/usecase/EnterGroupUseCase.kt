package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.EnterGroupModel
import org.threehundredtutor.domain.main.MainRepository
import javax.inject.Inject

class EnterGroupUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(key: String): EnterGroupModel =
        mainRepository.enterGroup(key = key)
}
