package com.v_points.app.data.solution

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.solution.models.BaseApiResponse
import com.v_points.app.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import com.v_points.app.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import com.v_points.app.data.solution.models.TestSolutionQueryDetailedResponse
import com.v_points.app.data.solution.models.TestSolutionQueryResponse
import com.v_points.app.data.solution.models.directory.StartTestDirectoryRequest
import com.v_points.app.data.solution.models.finish_test.FinishSolutionRequest
import com.v_points.app.data.solution.models.points.SolutionPointsResponse
import com.v_points.app.data.solution.models.request.ChangeLikeQuestionRequest
import com.v_points.app.data.solution.models.request.CheckAnswerRequest
import com.v_points.app.data.solution.models.request.QuestionSolutionIdRequest
import com.v_points.app.data.solution.models.request.SaveQuestionPointsValidationRequest
import com.v_points.app.data.solution.models.start_test.StartTestRequest
import com.v_points.app.data.solution.models.start_test.StartTestResponse
import javax.inject.Inject

class SolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionService::class) }

    suspend fun getSolution(solutionId: String): TestSolutionQueryResponse =
        service().getSolution(solutionId)

    suspend fun getSolutionDetailed(solutionId: String): TestSolutionQueryDetailedResponse =
        service().getSolutionDetailed(solutionId)

    suspend fun startByTestId(testId: String): StartTestResponse =
        service().startByTestId(
            StartTestRequest(
                testId = testId
            )
        )

    suspend fun startTestByDirectory(startTestDirectoryRequest: StartTestDirectoryRequest): StartTestResponse =
        service().startByDirectory(startTestDirectoryRequest)

    suspend fun checkAnswer(checkAnswerRequest: CheckAnswerRequest): QuestionAnswerWithResultBaseApiResponse =
        service().checkAnswer(checkAnswerRequest)

    suspend fun finish(finishSolutionRequest: FinishSolutionRequest): QuestionAnswersWithResultBaseApiResponse =
        service().finish(finishSolutionRequest)

    suspend fun resultQuestionsValidationSave(
        saveQuestionPointsValidationRequest: SaveQuestionPointsValidationRequest
    ): QuestionAnswerWithResultBaseApiResponse =
        service().resultQuestionsValidationSave(saveQuestionPointsValidationRequest)

    suspend fun resultQuestionsValidationRemove(
        questionSolutionIdRequest: QuestionSolutionIdRequest
    ): QuestionAnswerWithResultBaseApiResponse =
        service().resultQuestionsValidationRemove(questionSolutionIdRequest)

    suspend fun getResultPoints(solutionId: String): SolutionPointsResponse =
        service().getResultPoints(solutionId)

    suspend fun changeLikeQuestion(questionId: String, hasLike: Boolean): BaseApiResponse =
        service().changeLikeQuestion(
            ChangeLikeQuestionRequest(
                questionId = questionId,
                hasLike = hasLike
            )
        )

    suspend fun saveAnswers(finishSolutionRequest: FinishSolutionRequest): BaseApiResponse =
        service().saveAnswers(finishSolutionRequest)
}