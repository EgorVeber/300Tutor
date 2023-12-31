package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import javax.inject.Inject

class ResultQuestionsValidationRemoveUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        solutionId: String,
        questionId: String
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRepository.resultQuestionsValidationRemove(
            solutionId = solutionId,
            questionId = questionId,
        )
}