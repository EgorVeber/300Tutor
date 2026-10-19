package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import com.v_points.app.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import javax.inject.Inject

class ResultQuestionsValidationSaveUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel,
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRepository.resultQuestionsValidationSave(saveQuestionPointsValidationParamsModel)
}