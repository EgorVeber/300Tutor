package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class StartTestUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(testId: String): TestSolutionGeneralModel =
        solutionRepository.startByTestId(testId)
}
