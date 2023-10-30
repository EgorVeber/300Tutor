package org.threehundredtutor.data.solution

import org.threehundredtutor.data.authorization.models.LoginResponse
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_CHECK_ANSWER
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.data.solution.models.answer_response.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolutionService {
    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID)
    suspend fun getSolution(
        @Path("id") id: String
    ): TestSolutionQueryResponse

    @POST(TUTOR_TEST_SOLUTION_CHECK_ANSWER)
    suspend fun checkAnswer(@Body params: CheckAnswerRequest): QuestionAnswerWithResultBaseApiResponse
}
