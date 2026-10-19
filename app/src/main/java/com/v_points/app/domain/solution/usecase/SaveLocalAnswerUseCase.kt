package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
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
