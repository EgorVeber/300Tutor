package org.threehundredtutor.data.solution

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.data.solution.mappers.request.toSaveQuestionPointsValidationRequest
import org.threehundredtutor.data.solution.mappers.toBaseApiModel
import org.threehundredtutor.data.solution.mappers.toQuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.data.solution.mappers.toTestSolutionGeneralModel
import org.threehundredtutor.data.solution.models.request.AnswerRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionRemoteDataSource: SolutionRemoteDataSource,
) : SolutionRepository {
    override suspend fun getSolution(solutionId: String): TestSolutionGeneralModel =
        solutionRemoteDataSource.getSolution(solutionId).toTestSolutionGeneralModel()

    override suspend fun startByTestId(testId: String): TestSolutionGeneralModel =
        solutionRemoteDataSource.startByTestId(testId)?.toTestSolutionGeneralModel() ?: throw ServerException() // TODO сделать

    override suspend fun checkAnswer(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRemoteDataSource.checkAnswer(
            checkAnswerRequest = CheckAnswerRequest(
                solutionId = solutionId,
                answer = AnswerRequest(questionId = questionId, answerOrAnswers = answerOrAnswers)
            )
        ).toQuestionAnswerWithResultBaseApiModel()

    override suspend fun resultQuestionsValidationSave(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel
    ): BaseApiModel =
        solutionRemoteDataSource.resultQuestionsValidationSave(
            saveQuestionPointsValidationParamsModel.toSaveQuestionPointsValidationRequest()
        ).toBaseApiModel()
}
