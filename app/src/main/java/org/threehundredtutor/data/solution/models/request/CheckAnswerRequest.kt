package org.threehundredtutor.data.solution.models.request

import com.google.gson.annotations.SerializedName

class CheckAnswerRequest(
    @SerializedName("solutionId") val solutionId: String,
    @SerializedName("answer") val answer: AnswerRequest,
)

class AnswerRequest(
    @SerializedName("questionId") val questionId: String, //	string Если тип вопроса Tutor.Model.Enumerations.TestQuestionType.DetailedAnswer или Tutor.Model.Enumerations.TestQuestionType.TypeRightAnswer то это простая строка Для типа вопроса Tutor.Model.Enumerations.TestQuestionType.SelectRightAnswerOrAnswers это строка разделенная ; которая преобразуется в массив введенных ответов.
    @SerializedName("answerOrAnswers") val answerOrAnswers: String,
)
