package org.threehundredtutor.data.solution

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.data.solution.mappers.points.toSolutionPointsModel
import org.threehundredtutor.data.solution.mappers.request.toSaveQuestionPointsValidationRequest
import org.threehundredtutor.data.solution.mappers.toQuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.data.solution.mappers.toQuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.data.solution.mappers.toTestSolutionGeneralModel
import org.threehundredtutor.data.solution.models.finish_test.AnswerItemRequest
import org.threehundredtutor.data.solution.models.finish_test.FinishSolutionRequest
import org.threehundredtutor.data.solution.models.request.AnswerRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.data.solution.models.request.QuestionSolutionIdRequest
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionRemoteDataSource: SolutionRemoteDataSource,
    private val solutionLocalDataSource: SolutionLocalDataSource,
) : SolutionRepository {
    override suspend fun getSolution(solutionId: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel =
            solutionRemoteDataSource.getSolution(solutionId).toTestSolutionGeneralModel()
        solutionLocalDataSource.saveAnswers(testSolutionGeneralModel.testSolutionModel)
        return testSolutionGeneralModel
    }

    override suspend fun checkAnswer(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiModel {
        val questionAnswerWithResultBaseApiModel = solutionRemoteDataSource.checkAnswer(
            checkAnswerRequest = CheckAnswerRequest(
                solutionId = solutionId,
                answer = AnswerRequest(questionId = questionId, answerOrAnswers = answerOrAnswers)
            )
        ).toQuestionAnswerWithResultBaseApiModel()

        if (questionAnswerWithResultBaseApiModel.isSucceeded) {
            solutionLocalDataSource.saveAnswer(
                questionId = questionAnswerWithResultBaseApiModel.answerModel.questionId,
                answerOrAnswers = questionAnswerWithResultBaseApiModel.answerModel.answerOrAnswers
            )
        }

        return questionAnswerWithResultBaseApiModel
    }

    override suspend fun finishTest(solutionId: String): QuestionAnswersWithResultBaseApiModel {
        return solutionRemoteDataSource.finish(
            finishSolutionRequest = FinishSolutionRequest(
                solutionId = solutionId,
                answerList = solutionLocalDataSource.getAnswers().map {
                    AnswerItemRequest(
                        questionId = it.key,
                        answerOrAnswers = it.value
                    )
                },
            )
        ).toQuestionAnswersWithResultBaseApiModel()
    }

    override suspend fun startByTestId(testId: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel =
            solutionRemoteDataSource.startByTestId(testId)?.toTestSolutionGeneralModel()
                ?: throw ServerException()
        solutionLocalDataSource.saveAnswers(testSolutionGeneralModel.testSolutionModel)
        return testSolutionGeneralModel
    }

    override suspend fun resultQuestionsValidationSave(
        saveQuestionPointsValidationParamsModel: SaveQuestionPointsValidationParamsModel
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRemoteDataSource.resultQuestionsValidationSave(
            saveQuestionPointsValidationParamsModel.toSaveQuestionPointsValidationRequest()
        ).toQuestionAnswerWithResultBaseApiModel()

    override suspend fun resultQuestionsValidationRemove(
        solutionId: String,
        questionId: String
    ): QuestionAnswerWithResultBaseApiModel =
        solutionRemoteDataSource.resultQuestionsValidationRemove(
            QuestionSolutionIdRequest(
                solutionId = solutionId,
                questionId = questionId
            )
        ).toQuestionAnswerWithResultBaseApiModel()

    override fun isAllQuestionsHaveAnswers() = solutionLocalDataSource.isAllQuestionsHaveAnswers()

    override suspend fun getResultPoints(solutionId: String): SolutionPointsModel =
        solutionRemoteDataSource.getResultPoints(solutionId).toSolutionPointsModel()
}
