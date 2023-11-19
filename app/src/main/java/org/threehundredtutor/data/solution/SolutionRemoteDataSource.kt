package org.threehundredtutor.data.solution

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.solution.models.BaseApiResponse
import org.threehundredtutor.data.solution.models.StartTestParams
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.data.solution.models.request.SaveQuestionPointsValidationRequest
import javax.inject.Inject

class SolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionService::class) }

    suspend fun getSolution(solutionId: String): TestSolutionQueryResponse = service().getSolution(solutionId)
    suspend fun startByTestId(testId: String): TestSolutionQueryResponse? = service().startByTestId(
        StartTestParams(true, null, testId)
    ).testSolutionQueryResponse

    suspend fun checkAnswer(checkAnswerRequest: CheckAnswerRequest) =
        service().checkAnswer(checkAnswerRequest)

    suspend fun resultQuestionsValidationSave(
        saveQuestionPointsValidationRequest: SaveQuestionPointsValidationRequest
    ): BaseApiResponse =
        service().resultQuestionsValidationSave(saveQuestionPointsValidationRequest)
}