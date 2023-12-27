package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class IsAllQuestionHaveAnswerUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Boolean = solutionRepository.isAllQuestionsHaveAnswers()
}
