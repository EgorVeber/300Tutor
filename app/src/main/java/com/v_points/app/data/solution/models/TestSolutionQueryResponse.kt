package com.v_points.app.data.solution.models

import com.google.gson.annotations.SerializedName
import com.v_points.app.data.solution.models.solution_response.SolutionResponse
import com.v_points.app.data.solution.models.test_response.TestResponse

class TestSolutionQueryResponse(
    @SerializedName("solutionId")
    val solutionId: String?,
    @SerializedName("hasCuratorValidation")
    val hasCuratorValidation: Boolean?,
    @SerializedName("canCheckSingleQuestion")
    val canCheckSingleQuestion: Boolean?,//Можно ли проверять каждый вопрос в тесте на правильность
    @SerializedName("studentGroupId")
    val studentGroupId: String?,
    @SerializedName("studentId")
    val studentId: String?,
    @SerializedName("startedOnUtc")
    val startedOnUtc: String?,
    @SerializedName("finishedOnUtc")
    val finishedOnUtc: String?,
    @SerializedName("isFinished")
    val isFinished: Boolean?,
    @SerializedName("solution")
    val solutionResponse: SolutionResponse?,
    @SerializedName("test")
    val testResponse: TestResponse?
)