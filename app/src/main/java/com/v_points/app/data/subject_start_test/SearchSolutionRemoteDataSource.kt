package com.v_points.app.data.subject_start_test

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.solution_history.SolutionHistoryService
import com.v_points.app.data.solution_history.models.request.SearchSolutionHistoryRequest
import com.v_points.app.data.solution_history.models.response.SearchSolutionGeneralResponse
import javax.inject.Inject

class SearchSolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SolutionHistoryService::class) }

    suspend fun searchSolution(searchSolutionHistoryRequest: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse =
        service().searchSolution(searchSolutionHistoryRequest)
}