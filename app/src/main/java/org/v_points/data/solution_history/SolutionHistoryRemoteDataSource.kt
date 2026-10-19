package org.v_points.data.solution_history

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.v_points.data.solution_history.models.response.SearchSolutionGeneralResponse
import javax.inject.Inject

class SolutionHistoryRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionHistoryService::class) }

    suspend fun searchSolution(searchSolutionHistoryRequest: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse =
        service().searchSolution(searchSolutionHistoryRequest)
}