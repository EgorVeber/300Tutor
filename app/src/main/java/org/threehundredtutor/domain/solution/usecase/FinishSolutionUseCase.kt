package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import javax.inject.Inject

class FinishSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        solutionId: String,
    ): QuestionAnswersWithResultBaseApiModel =
        solutionRepository.finishTest(solutionId)
}
