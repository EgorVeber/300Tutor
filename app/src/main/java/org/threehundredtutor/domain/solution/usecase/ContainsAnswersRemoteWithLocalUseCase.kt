package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class ContainsAnswersRemoteWithLocalUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Boolean = solutionRepository.isAnyQuestionsHaveAnswers()
}