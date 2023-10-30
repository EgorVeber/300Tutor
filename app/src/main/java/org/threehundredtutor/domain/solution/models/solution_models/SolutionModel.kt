package org.threehundredtutor.domain.solution.models.solution_models

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.EMPTY_STRING

data class SolutionModel(
    val answerModelList: List<AnswerModel> //Ответ на вопрос с проверкой
) {
    companion object {
        val EMPTY = SolutionModel(
            answerModelList = emptyList()
        )
    }
}

data class AnswerModel(
    //Если тип вопроса Tutor.Model.Enumerations.TestQuestionType.DetailedAnswer или Tutor.Model.Enumerations.TestQuestionType.TypeRightAnswer то это простая строка
    //Для типа вопроса Tutor.Model.Enumerations.TestQuestionType.SelectRightAnswerOrAnswers это строка разделенная ; которая преобразуется в массив введенных ответов.
    val answerOrAnswers: String,
    val isChecked: Boolean,//Является ли вопрос провренным
    val pointsValidationModel: PointsValidationModel,
    val questionId: String,//Идентификатор вопроса
    val questionVersionId: String,//Идентификатор версии вопроса
    val answerValidationResultType: AnswerValidationResultType //[ NeedToCheckByYourSelf, NotCorrectAnswer, PartiallyCorrectAnswer, CorrectAnswer ]
)

data class PointsValidationModel(
    val answerPoints: Int,
    val description: String,
    val isValidated: Boolean,
    val questionTotalPoints: Int
) {

    companion object {
        val EMPTY = PointsValidationModel(
            answerPoints = DEFAULT_NOT_VALID_VALUE_INT,
            description = EMPTY_STRING,
            isValidated = false,
            questionTotalPoints = DEFAULT_NOT_VALID_VALUE_INT,
        )
    }
}