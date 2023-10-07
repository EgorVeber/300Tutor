package org.threehundredtutor.data.solution.models.answer_response

data class QuestionAnswerWithResultBaseApiResponse(
    var isSucceeded: Boolean,
    var message: String,
    var responseObject: ResponseObject
)