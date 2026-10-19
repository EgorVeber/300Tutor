package org.v_points.data.solution.models.test_response

import com.google.gson.annotations.SerializedName
import org.v_points.data.solution.models.solution_response.AnswerResponse

class QuestionDetailedResponse(
    @SerializedName("answerWithValidation")
    val answerWithValidation: AnswerResponse?,
    @SerializedName("isQuestionLikedByStudent")
    val isQuestionLikedByStudent: Boolean?,
    @SerializedName("question")
    val question: QuestionResponse?
)