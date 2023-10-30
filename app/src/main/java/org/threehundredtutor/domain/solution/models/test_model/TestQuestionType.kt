package org.threehundredtutor.domain.solution.models.test_model

enum class TestQuestionType {
    SELECT_RIGHT_ANSWER_OR_ANSWERS,
    TYPE_RIGHT_ANSWER,
    DETAILED_ANSWER,
    TYPE_ANSWER_WITH_ERRORS,
    UNKNOWN;

    companion object {

        private const val SelectRightAnswerOrAnswers = "SelectRightAnswerOrAnswers"
        private const val TypeRightAnswer = "TypeRightAnswer"
        private const val DetailedAnswer = "DetailedAnswer"
        private const val TypeAnswerWithErrors = "TypeAnswerWithErrors"

        fun getType(questionType: String) =
            when (questionType) {
                SelectRightAnswerOrAnswers -> SELECT_RIGHT_ANSWER_OR_ANSWERS
                TypeRightAnswer -> TYPE_RIGHT_ANSWER
                DetailedAnswer -> DETAILED_ANSWER
                TypeAnswerWithErrors -> TYPE_ANSWER_WITH_ERRORS
                else -> UNKNOWN
            }
    }
}