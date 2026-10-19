package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import javax.inject.Inject

class FinishSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        solutionId: String,
    ): QuestionAnswersWithResultBaseApiModel =
        solutionRepository.finishTest(solutionId)
}
