package org.threehundredtutor.data.solution_history

import org.threehundredtutor.data.solution_history.SolutionHistoryApi.TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH
import org.threehundredtutor.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.threehundredtutor.data.solution_history.models.response.SearchSolutionGeneralResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SolutionHistoryService {
    @POST(TUTOR_TEST_SOLUTION_QUERY_MINE_SEARCH)
    suspend fun searchSolution(@Body params: SearchSolutionHistoryRequest): SearchSolutionGeneralResponse
}
