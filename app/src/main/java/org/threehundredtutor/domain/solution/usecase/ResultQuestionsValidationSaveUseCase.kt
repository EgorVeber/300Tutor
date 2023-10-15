package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import javax.inject.Inject

class ResultQuestionsValidationSaveUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel,
    ): BaseApiModel =
        solutionRepository.resultQuestionsValidationSave(saveQuestionPointsValidationParamsModel)
}