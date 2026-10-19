package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.QuestionAnswerWithResultBaseApiModel
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