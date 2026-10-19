package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import javax.inject.Inject

class ContainsAnswersRemoteWithLocalUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Boolean = solutionRepository.isAnyQuestionsHaveAnswers()
}