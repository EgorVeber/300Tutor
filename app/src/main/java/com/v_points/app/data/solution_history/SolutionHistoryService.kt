package com.v_points.app.data.solution_history

import com.v_points.app.data.solution_history.SolutionHistoryApi.TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH
import com.v_points.app.data.solution_history.models.request.SearchSolutionHistoryRequest
import com.v_points.app.data.solution_history.models.response.SearchSolutionGeneralResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SolutionHistoryService {
    @POST(TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH)
    suspend fun searchSolution(@Body params: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse
}
