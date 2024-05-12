package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class GetSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionIdInt: String): TestSolutionGeneralModel =
        solutionRepository.getSolutionDetailed(solutionIdInt)
}
