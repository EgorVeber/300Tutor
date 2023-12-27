package org.threehundredtutor.data.solution.models.start_test

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse

class StartTestResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val testSolutionQueryResponse: TestSolutionQueryResponse?,
)