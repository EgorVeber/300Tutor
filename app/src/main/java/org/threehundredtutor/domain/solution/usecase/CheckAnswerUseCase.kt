package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.data.solution.models.answer_response.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel
import javax.inject.Inject

class CheckAnswerUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiResponse =
        solutionRepository.checkAnswer(
            solutionId = solutionId,
            questionId = questionId,
            answerOrAnswers = answerOrAnswers
        )
}
