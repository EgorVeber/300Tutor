package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.QuestionAnswerWithResultBaseApiModel
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