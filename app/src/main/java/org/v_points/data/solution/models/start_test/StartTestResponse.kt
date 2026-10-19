package org.v_points.data.solution.models.start_test

import com.google.gson.annotations.SerializedName
import org.v_points.data.solution.models.TestSolutionQueryResponse

class StartTestResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val testSolutionQueryResponse: TestSolutionQueryResponse?,
)