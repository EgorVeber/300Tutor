package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.TestSolutionGeneralModel
import javax.inject.Inject

class StartTestUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(testId: String): TestSolutionGeneralModel =
        solutionRepository.startByTestId(testId)
}
