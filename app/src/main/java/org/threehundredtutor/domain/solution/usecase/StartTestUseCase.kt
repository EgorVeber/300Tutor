package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class StartTestUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(testId: String): TestSolutionGeneralModel =
        solutionRepository.startByTestId(testId)
}
