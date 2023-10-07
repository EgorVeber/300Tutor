package org.threehundredtutor.domain.solution.models.solution_models

enum class AnswerValidationResultType {
    NEED_TO_CHECK_BY_YOUR_SELF,// Проверить самостоятельно
    NOT_CORRECT_ANSWER,// Неправильный ответ
    PARTIALLY_CORRECT_ANSWER, // Частично
    CORRECT_ANSWER, // Правльный
    UNKNOWN;

    companion object {

        private const val NeedToCheckByYourSelf = "NeedToCheckByYourSelf"
        private const val NotCorrectAnswer = "NotCorrectAnswer"
        private const val PartiallyCorrectAnswer = "PartiallyCorrectAnswer"
        private const val CorrectAnswer = "CorrectAnswer"

        fun getType(answerValidationResultType: String): AnswerValidationResultType =
            when (answerValidationResultType) {
                NeedToCheckByYourSelf -> NEED_TO_CHECK_BY_YOUR_SELF
                NotCorrectAnswer -> NOT_CORRECT_ANSWER
                PartiallyCorrectAnswer -> PARTIALLY_CORRECT_ANSWER
                CorrectAnswer -> CORRECT_ANSWER
                else -> UNKNOWN
            }
    }
}