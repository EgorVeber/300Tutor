package org.threehundredtutor.data.solution

import org.threehundredtutor.data.solution.mappers.points.toSolutionPointsModel
import org.threehundredtutor.data.solution.mappers.request.toSaveQuestionPointsValidationRequest
import org.threehundredtutor.data.solution.mappers.toBaseApiModel
import org.threehundredtutor.data.solution.mappers.toQuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.data.solution.mappers.toQuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.data.solution.mappers.toTestSolutionGeneralModel
import org.threehundredtutor.data.solution.models.directory.toStartTestDirectoryRequest
import org.threehundredtutor.data.solution.models.finish_test.AnswerItemRequest
import org.threehundredtutor.data.solution.models.finish_test.FinishSolutionRequest
import org.threehundredtutor.data.solution.models.request.AnswerRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.data.solution.models.request.QuestionSolutionIdRequest
import org.threehundredtutor.data.solution.models.start_test.StartTestResponse
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.directory.StartTestDirectoryParamsModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import org.threehundredtutor.domain.solution.models.test_model.TestSolutionModel
import org.threehundredtutor.ui_common.util.ServerException
import org.threehundredtutor.ui_common.util.orFalse
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionRemoteDataSource: SolutionRemoteDataSource,
    private val solutionLocalDataSource: SolutionLocalDataSource,
) : SolutionRepository {

    //TODO удалить старые модели если в дальнейшем небудет необходимости
    override suspend fun getSolution(solutionId: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel =
            solutionRemoteDataSource.getSolution(solutionId).toTestSolutionGeneralModel()
        solutionLocalDataSource.saveAnswers(answersToMap(testSolutionGeneralModel.testSolutionModel))
        return testSolutionGeneralModel
    }

    override suspend fun getSolutionDetailed(solutionId: String): TestSolutionGeneralModel {
        val testSolutionGeneralModel =
            solutionRemoteDataSource.getSolutionDetailed(solutionId).toTestSolutionGeneralModel()

        solutionLocalDataSource.saveAnswers(answersToMap(testSolutionGeneralModel.testSolutionModel))
        return testSolutionGeneralModel
    }


    override suspend fun startByTestId(testId: String): TestSolutionGeneralModel {
        val startTestResponse: StartTestResponse =
            solutionRemoteDataSource.startByTestId(testId)
        return handleStartTest(startTestResponse)
    }

    override suspend fun startByDirectory(startTestDirectoryParamsModel: StartTestDirectoryParamsModel): TestSolutionGeneralModel {
        val startTestResponse: StartTestResponse =
            solutionRemoteDataSource.startTestByDirectory(startTestDirectoryParamsModel.toStartTestDirectoryRequest())
        return handleStartTest(startTestResponse)
    }

    private suspend fun handleStartTest(
        startTestResponse: StartTestResponse
    ): TestSolutionGeneralModel {
        val solutionId: String =
            startTestResponse.testSolutionQueryResponse?.solutionId ?: throw ServerException()
        if (startTestResponse.isSucceeded.orFalse() && solutionId.isNotEmpty()) {
            return getSolutionDetailed(startTestResponse.testSolutionQueryResponse.solutionId)
        } else {
            throw ServerException()
        }
    }

    // TODO Сделать локальное сохранение ответов.
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
                questionAnswerWithResultBaseApiModel.answerModel.questionId to
                        questionAnswerWithResultBaseApiModel.answerModel.answerOrAnswers
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

    override fun isAllQuestionsHaveAnswers() =
        solutionLocalDataSource.getAnswers().values.all { it.isNotEmpty() }

    override fun getSolutionAnswersFlow() = solutionLocalDataSource.getSolutionAnswersFlow()

    override suspend fun getResultPoints(solutionId: String): SolutionPointsModel =
        solutionRemoteDataSource.getResultPoints(solutionId).toSolutionPointsModel()

    override suspend fun changeLikeQuestion(
        questionId: String,
        hasLike: Boolean
    ): BaseApiModel =
        solutionRemoteDataSource.changeLikeQuestion(questionId, hasLike).toBaseApiModel()

    private fun answersToMap(testSolutionModelList: List<TestSolutionModel>) =
        testSolutionModelList.associate { testSolutionModel ->
            testSolutionModel.questionModel.questionId to testSolutionModel.answerModel.answerOrAnswers
        }
}