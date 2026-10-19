package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import javax.inject.Inject

class SaveRemoteAnswersUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionId: String) =
        solutionRepository.saveRemoteAnswers(solutionId)
}
