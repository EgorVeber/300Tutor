package org.threehundredtutor.domain.solution

import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel

interface SolutionRepository {
    suspend fun getSolution(solutionId: String): TestSolutionGeneralModel
    suspend fun startByTestId(testId: String): TestSolutionGeneralModel
    suspend fun checkAnswer(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiModel

    suspend fun resultQuestionsValidationSave(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel
    ): BaseApiModel
}
