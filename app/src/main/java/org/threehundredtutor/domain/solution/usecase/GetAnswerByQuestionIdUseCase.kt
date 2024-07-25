package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class GetAnswerByQuestionIdUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(questionId: String): String =
        solutionRepository.getAnswerByQuestionId(questionId)
}
