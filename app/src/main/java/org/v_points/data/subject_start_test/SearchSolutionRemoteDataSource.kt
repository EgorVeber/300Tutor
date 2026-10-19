package org.v_points.data.subject_start_test

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.solution_history.SolutionHistoryService
import org.v_points.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.v_points.data.solution_history.models.response.SearchSolutionGeneralResponse
import javax.inject.Inject

class SearchSolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionHistoryService::class) }

    suspend fun searchSolution(searchSolutionHistoryRequest: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse =
        service().searchSolution(searchSolutionHistoryRequest)
}