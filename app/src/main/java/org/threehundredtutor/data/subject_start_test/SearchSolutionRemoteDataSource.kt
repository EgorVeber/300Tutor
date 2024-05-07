package org.threehundredtutor.data.subject_start_test

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.solution_history.SolutionHistoryService
import org.threehundredtutor.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.threehundredtutor.data.solution_history.models.response.SearchSolutionGeneralResponse
import javax.inject.Inject

class SearchSolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionHistoryService::class) }

    suspend fun searchSolution(searchSolutionHistoryRequest: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse =
        service().searchSolution(searchSolutionHistoryRequest)
}