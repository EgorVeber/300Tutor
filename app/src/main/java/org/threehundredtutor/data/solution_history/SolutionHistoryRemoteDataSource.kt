package org.threehundredtutor.data.solution_history

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.threehundredtutor.data.solution_history.models.response.SearchSolutionGeneralResponse
import javax.inject.Inject

class SolutionHistoryRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionHistoryService::class) }

    suspend fun searchSolution(): SearchSolutionGeneralResponse = service().searchSolution(
        SearchSolutionHistoryRequest(
            count = null,
            isFinished = null,
            offSet = 0,
            testId = null
        )
    )
}