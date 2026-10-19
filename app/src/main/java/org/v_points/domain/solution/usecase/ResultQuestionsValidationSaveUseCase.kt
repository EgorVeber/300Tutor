package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.v_points.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import javax.inject.Inject

class ResultQuestionsValidationSaveUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel,
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRepository.resultQuestionsValidationSave(saveQuestionPointsValidationParamsModel)
}