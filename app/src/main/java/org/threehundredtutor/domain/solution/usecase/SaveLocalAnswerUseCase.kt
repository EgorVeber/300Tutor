package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class SaveLocalAnswerUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(
        questionId: String,
        answerOrAnswers: String,
    ) {
        solutionRepository.saveLocalAnswer(
            questionId = questionId,
            answerOrAnswers = answerOrAnswers
        )
    }
}
