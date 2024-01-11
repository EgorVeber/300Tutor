package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class GetSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionIdInt: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel = solutionRepository.getSolutionDetailed(solutionIdInt)
        return testSolutionGeneralModel.copy(
            testSolutionModel = testSolutionGeneralModel.testSolutionModel.sortedBy { it.questionModel.title }
        )
    }
}
