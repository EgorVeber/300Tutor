package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import javax.inject.Inject

class FinishSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        solutionId: String,
    ): QuestionAnswersWithResultBaseApiModel =
        solutionRepository.finishTest(solutionId)
}
