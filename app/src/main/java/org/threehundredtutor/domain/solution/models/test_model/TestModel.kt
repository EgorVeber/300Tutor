package org.threehundredtutor.domain.solution.models.test_model

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.EMPTY_STRING

data class TestModel(
    val description: String,
    val name: String,
    val questionList: List<QuestionModel>,
) {
    companion object {
        val EMPTY = TestModel(
            description = EMPTY_STRING,
            name = EMPTY_STRING,
            questionList = emptyList(),
        )
    }
}

data class QuestionModel(
    val id: String, //
    val title: String,
    val titleBodyMarkUp: String,//Разметка заголовка вопроса
    val helpBodyMarkUp: String,//Разметка подскази по вопросу
    val answerExplanationMarkUp: String,  //Разметка объяснения ответа на вопрос
    val versionId: String,// Идентификатор версии вопроса
    val testQuestionType: TestQuestionType,// Тип вопроса [ SelectRightAnswerOrAnswers, TypeRightAnswer, DetailedAnswer, TypeAnswerWithErrors ]
    val selectRightAnswerOrAnswersDataModel: SelectRightAnswerOrAnswersDataModel, //Данные для построения интерфейса для вопроса с типом "Выбрать правильный ответ"
    val typeRightAnswerQuestionDataModel: TypeRightAnswerQuestionDataModel,// Данные для предоставления интерфейса и логики для типа вопроса "Ввести правильный ответ"
    val typeAnswerWithErrorsDataModel: TypeAnswerWithErrorsDataModel, // Данные для построения интерфейса для вопроса с типом "Ввести правильный ответ с поддержкой ошибок"
)

//Данные для построения интерфейса для вопроса с типом "Выбрать правильный ответ"
data class SelectRightAnswerOrAnswersDataModel(
    val answersList: List<AnswerXModel>, //	 Варианты ответов на вопрос
    val rightAnswersCount: Int,// Количество правильных ответов
    val selectRightAnswerTitle: String,//  Текст выбора правильного ответа
) {
    companion object {
        val EMPTY = SelectRightAnswerOrAnswersDataModel(
            answersList = emptyList(),
            rightAnswersCount = DEFAULT_NOT_VALID_VALUE_INT,
            selectRightAnswerTitle = EMPTY_STRING
        )
    }
}

data class AnswerXModel(
    val isRightAnswer: Boolean,// Правильный или нет.
    val text: String, // Текс ответа,
)

data class TypeAnswerWithErrorsDataModel(
    val rightAnswer: String //Текст правильного ответа без ошибок
) {
    companion object {
        val EMPTY = TypeAnswerWithErrorsDataModel(
            rightAnswer = EMPTY_STRING
        )
    }
}

data class TypeRightAnswerQuestionDataModel(
    val caseInSensitive: Boolean, //Не учитывать регистр букв caseInSensitive true = Не учитываем регистр  - Делаем Caps Инача не делаем
    val rightAnswers: List<String> //Набор правильных ответов
) {
    companion object {
        val EMPTY = TypeRightAnswerQuestionDataModel(
            caseInSensitive = false,
            rightAnswers = emptyList()
        )
    }
}


