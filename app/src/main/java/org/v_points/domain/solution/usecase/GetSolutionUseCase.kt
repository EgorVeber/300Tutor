package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class GetSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionIdInt: String): TestSolutionGeneralModel =
        solutionRepository.getSolutionDetailed(solutionIdInt)
}
