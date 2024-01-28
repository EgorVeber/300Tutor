package org.threehundredtutor.domain.solution

import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel

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
    ): QuestionAnswerWithResultBaseApiModel

    suspend fun finishTest(solutionId: String): QuestionAnswersWithResultBaseApiModel
    suspend fun getResultPoints(solutionId: String): SolutionPointsModel
    fun isAllQuestionsHaveAnswers(): Boolean
    suspend fun resultQuestionsValidationRemove(
        solutionId: String,
        questionId: String
    ): QuestionAnswerWithResultBaseApiModel

    suspend fun getSolutionDetailed(solutionId: String): TestSolutionGeneralModel
    suspend fun changeLikeQuestion(questionId: String, hasLike: Boolean): BaseApiModel
}
