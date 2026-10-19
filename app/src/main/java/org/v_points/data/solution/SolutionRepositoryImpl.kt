package org.v_points.data.solution

import org.v_points.data.solution.mappers.points.toSolutionPointsModel
import org.v_points.data.solution.mappers.request.toSaveQuestionPointsValidationRequest
import org.v_points.data.solution.mappers.toBaseApiModel
import org.v_points.data.solution.mappers.toQuestionAnswerWithResultBaseApiModel
import org.v_points.data.solution.mappers.toQuestionAnswersWithResultBaseApiModel
import org.v_points.data.solution.mappers.toTestSolutionGeneralModel
import org.v_points.data.solution.models.directory.toStartTestDirectoryRequest
import org.v_points.data.solution.models.finish_test.AnswerItemRequest
import org.v_points.data.solution.models.finish_test.FinishSolutionRequest
import org.v_points.data.solution.models.request.AnswerRequest
import org.v_points.data.solution.models.request.CheckAnswerRequest
import org.v_points.data.solution.models.request.QuestionSolutionIdRequest
import org.v_points.data.solution.models.start_test.StartTestResponse
import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.BaseApiModel
import org.v_points.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.v_points.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.v_points.domain.solution.models.TestSolutionGeneralModel
import org.v_points.domain.solution.models.directory.StartTestDirectoryParamsModel
import org.v_points.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.v_points.domain.solution.models.points.SolutionPointsModel
import org.v_points.domain.solution.models.test_model.TestSolutionModel
import org.v_points.ui_common.util.ServerException
import org.v_points.ui_common.util.orFalse
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
            solutionLocalDataSource.saveCheckAnswer(
                questionAnswerWithResultBaseApiModel.answerModel.questionId to
                        questionAnswerWithResultBaseApiModel.answerModel.answerOrAnswers
            )
        }

        return questionAnswerWithResultBaseApiModel
    }

    override fun saveLocalAnswer(
        questionId: String,
        answerOrAnswers: String
    ) {
        solutionLocalDataSource.saveAnswer(questionId to answerOrAnswers)
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

    override suspend fun saveRemoteAnswers(solutionId: String): BaseApiModel {
        return solutionRemoteDataSource.saveAnswers(
            finishSolutionRequest = FinishSolutionRequest(
                solutionId = solutionId,
                answerList = solutionLocalDataSource.getAnswers().map {
                    AnswerItemRequest(
                        questionId = it.key,
                        answerOrAnswers = it.value
                    )
                },
            )
        ).toBaseApiModel()
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

    override fun getAnswerByQuestionId(questionId: String): String {
        return solutionLocalDataSource.getAnswers()[questionId].orEmpty()
    }

    override fun isAnyQuestionsHaveAnswers(): Boolean {
        val checkedQuestions = solutionLocalDataSource.getListCheckedQuestion()
        val currentAnswer = solutionLocalDataSource.getAnswers().filter { (key, value) ->
            !checkedQuestions.contains(key)
        }
        val initAnswer = solutionLocalDataSource.getInitAnswers().filter { (key, value) ->
            !checkedQuestions.contains(key)
        }
        return currentAnswer == initAnswer
    }

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