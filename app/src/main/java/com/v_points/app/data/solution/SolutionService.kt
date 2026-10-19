package com.v_points.app.data.solution

import com.v_points.app.data.solution.SolutionApi.ID
import com.v_points.app.data.solution.SolutionApi.SOLUTION_ID
import com.v_points.app.data.solution.SolutionApi.TUTOR_QUESTION_LIKES_CHANGE
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_CHECK_ANSWER
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_FINISH
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID_DETAILED
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_GET_BY_ID
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_POINTS
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_SAVE_ANSWERS_WITH_HOUT
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_START_BY_DIRECTORY
import com.v_points.app.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_START_BY_TEST_ID
import com.v_points.app.data.solution.models.BaseApiResponse
import com.v_points.app.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import com.v_points.app.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import com.v_points.app.data.solution.models.TestSolutionQueryDetailedResponse
import com.v_points.app.data.solution.models.TestSolutionQueryResponse
import com.v_points.app.data.solution.models.directory.StartTestDirectoryRequest
import com.v_points.app.data.solution.models.finish_test.FinishSolutionRequest
import com.v_points.app.data.solution.models.points.SolutionPointsResponse
import com.v_points.app.data.solution.models.request.ChangeLikeQuestionRequest
import com.v_points.app.data.solution.models.request.CheckAnswerRequest
import com.v_points.app.data.solution.models.request.QuestionSolutionIdRequest
import com.v_points.app.data.solution.models.request.SaveQuestionPointsValidationRequest
import com.v_points.app.data.solution.models.start_test.StartTestRequest
import com.v_points.app.data.solution.models.start_test.StartTestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolutionService {
    @GET(TUTOR_TEST_SOLUTION_QUERY_GET_BY_ID)
    suspend fun getSolution(@Path(ID) id: String): TestSolutionQueryResponse

    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID_DETAILED)
    suspend fun getSolutionDetailed(@Path(ID) id: String): TestSolutionQueryDetailedResponse

    @POST(TUTOR_TEST_SOLUTION_START_BY_TEST_ID)
    suspend fun startByTestId(@Body params: StartTestRequest): StartTestResponse

    @POST(TUTOR_TEST_SOLUTION_START_BY_DIRECTORY)
    suspend fun startByDirectory(@Body params: StartTestDirectoryRequest): StartTestResponse

    @POST(TUTOR_TEST_SOLUTION_FINISH)
    suspend fun finish(@Body params: FinishSolutionRequest): QuestionAnswersWithResultBaseApiResponse
    @POST(TUTOR_TEST_SOLUTION_SAVE_ANSWERS_WITH_HOUT)
    suspend fun saveAnswers(@Body params: FinishSolutionRequest): BaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE)
    suspend fun resultQuestionsValidationSave(@Body params: SaveQuestionPointsValidationRequest): QuestionAnswerWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE)
    suspend fun resultQuestionsValidationRemove(@Body params: QuestionSolutionIdRequest): QuestionAnswerWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_CHECK_ANSWER)
    suspend fun checkAnswer(@Body params: CheckAnswerRequest): QuestionAnswerWithResultBaseApiResponse

    @GET(TUTOR_TEST_SOLUTION_RESULT_POINTS)
    suspend fun getResultPoints(@Path(SOLUTION_ID) solutionId: String): SolutionPointsResponse

    @POST(TUTOR_QUESTION_LIKES_CHANGE)
    suspend fun changeLikeQuestion(@Body params: ChangeLikeQuestionRequest): BaseApiResponse
}
