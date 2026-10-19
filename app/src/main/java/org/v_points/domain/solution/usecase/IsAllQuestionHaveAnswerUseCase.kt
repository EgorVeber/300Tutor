package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import javax.inject.Inject

class IsAllQuestionHaveAnswerUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Boolean = solutionRepository.isAllQuestionsHaveAnswers()
}
