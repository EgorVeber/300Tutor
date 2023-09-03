package org.threehundredtutor.data.solution

import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface SolutionService {
    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID)
    suspend fun getSolution(
        @Path("id") id: String
    ): TestSolutionQueryResponse
}
