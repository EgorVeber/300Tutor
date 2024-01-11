package org.threehundredtutor.data.solution.models.test_response

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.solution_response.AnswerResponse

class QuestionDetailedResponse(
    @SerializedName("answerWithValidation")
    val answerWithValidation: AnswerResponse?,
    @SerializedName("isQuestionLikedByStudent")
    val isQuestionLikedByStudent: Boolean?,
    @SerializedName("question")
    val question: QuestionResponse?
)