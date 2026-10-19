package org.v_points.data.subject_start_test

import org.v_points.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.v_points.data.solution_history.models.response.SearchSolutionGeneralResponse
import org.v_points.data.subject_start_test.SearchSolutionApi.TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchSolutionService {
    @POST(TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH)
    suspend fun searchSolution(@Body params: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse
}
