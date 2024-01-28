package org.threehundredtutor.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class TestResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("questions")
    val questionResponses: List<QuestionResponse>?
)