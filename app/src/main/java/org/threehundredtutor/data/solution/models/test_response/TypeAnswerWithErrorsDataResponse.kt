package org.threehundredtutor.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class TypeAnswerWithErrorsDataResponse(
    @SerializedName("rightAnswer")
    val rightAnswer: String?
)