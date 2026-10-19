package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import javax.inject.Inject

class IsAllQuestionHaveAnswerUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Boolean = solutionRepository.isAllQuestionsHaveAnswers()
}
