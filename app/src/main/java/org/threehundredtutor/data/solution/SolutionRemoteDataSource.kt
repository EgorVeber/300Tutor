package org.threehundredtutor.data.solution

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.solution.models.request.AnswerRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import javax.inject.Inject

class SolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: SolutionService =
        serviceGeneratorProvider.getService(SolutionService::class)

    suspend fun getSolution(solutionId: String) = service.getSolution(solutionId)
    suspend fun checkAnswer(checkAnswerRequest: CheckAnswerRequest) =
        service.checkAnswer(checkAnswerRequest)

}