package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class SaveRemoteAnswersUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionId: String) =
        solutionRepository.saveRemoteAnswers(solutionId)
}
