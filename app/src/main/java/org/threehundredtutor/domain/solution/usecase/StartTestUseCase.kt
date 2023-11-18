package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class StartTestUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(testId: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel = solutionRepository.startByTestId(testId)
        return testSolutionGeneralModel.copy(
            testSolutionModel = testSolutionGeneralModel.testSolutionModel.sortedBy { it.questionModel.title }
        )
    }
}
