package org.v_points.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class TestResponseDetailed(
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("questions")
    val questionResponses: List<QuestionDetailedResponse>?
)