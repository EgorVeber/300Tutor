package org.threehundredtutor.presentation.solution

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.presentation.solution.mapper.extractHtml
import org.threehundredtutor.presentation.solution.model.AnswerUiModel
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.QuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.model.QuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.model.SelectRightAnswerOrAnswersUiModel
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    getSolutionUseCase: GetSolutionUseCase, private val checkAnswerUseCase: CheckAnswerUseCase
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<HtmlItem>>(listOf())

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()

    // TODO унести в дата сорс.
    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val mainModel: TestSolutionQueryModel = getSolutionUseCase.invoke(CURRENT_SOLUTION_ID)
            val questionList: List<QuestionModel> = mainModel.testModel.questionList
            val answerModelList: List<AnswerModel> = mainModel.solutionModel.answerModelList

            val uiItemList: List<HtmlItem> = questionList.map { question ->
                question.extractHtml(mainModel.solutionId)
            }.flatten()

            uiItemsState.update { uiItemList }

        }, catchBlock = { throwable ->
            Log.d("getSolution", throwable.toString())
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun answerWithErrorsClicked(
        questionAnswerWithErrorsUiModel: QuestionAnswerWithErrorsUiModel, answer: String
    ) {
        // TODO возможно надо сохранать текущее рещение в дата сорсе. Чтоб потом сразу отправить на сервер.
        // TODO Можеть быть надо сразу сохранять индексы чтоб потом не страдать хуйней.
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val result = checkAnswerUseCase(
                solutionId = questionAnswerWithErrorsUiModel.solutionId,
                questionId = questionAnswerWithErrorsUiModel.questionId,
                answerOrAnswers = answer
            )
            Log.d(TAG, result.toString())

            val currentList = uiItemsState.value.toMutableList()
            val index = currentList.indexOf(questionAnswerWithErrorsUiModel)
            if (index == -1) return@launchJob

            currentList.removeAt(index)
            currentList.add(
                index, AnswerUiModel(
                    answer = answer,
                    rightAnswer = questionAnswerWithErrorsUiModel.rightAnswer,
                    answerValidationResultType = AnswerValidationResultType.getType(result.responseObject.resultType)
                )
            )

            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
            Log.d(TAG, "checkAnswerClicked() called with: throwable = $throwable")
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun itemChecked(answerText: String, checked: Boolean, questionId: String) {
        val answersList = questionMap.getOrPut(key = questionId, defaultValue = { emptyList() })
        if (checked) {
            questionMap[questionId] = answersList + listOf(answerText)
        } else {
            questionMap[questionId] = answersList - listOf(answerText).toSet()
        }
    }

    fun selectRightAnswerOrAnswersClick(selectRightAnswerOrAnswersUiModel: SelectRightAnswerOrAnswersUiModel) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val answer =
                questionMap.getOrPut(selectRightAnswerOrAnswersUiModel.questionId) { emptyList() }
                    .joinToString(separator = ";")

            val result = checkAnswerUseCase(
                solutionId = selectRightAnswerOrAnswersUiModel.solutionId,
                questionId = selectRightAnswerOrAnswersUiModel.questionId,
                answerOrAnswers = answer
            )
            Log.d(TAG, "checkAnswerClicked() called with: throwable = $result")
        }, catchBlock = { throwable ->
            Log.d(TAG, "checkAnswerClicked() called with: throwable = $throwable")
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun detailedAnswerClicked(
        questionAnswerWithErrorsUiModel: QuestionDetailedAnswerUiModel, answer: String
    ) {
        // TODO показать разьеснение ответа и добавить item для самостоятельной оценки.
    }


    companion object {
        var TAG = this::class.java.name
        const val CURRENT_SOLUTION_ID = "7c5e2349-2301-4125-9ddd-2dbefaddde69"
    }
}