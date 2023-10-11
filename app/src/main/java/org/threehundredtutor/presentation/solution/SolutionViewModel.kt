package org.threehundredtutor.presentation.solution

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.presentation.solution.mapper.extractHtml
import org.threehundredtutor.presentation.solution.model.AnswerUiModel
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.QuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.model.QuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.model.QuestionRightAnswerUiModel
import org.threehundredtutor.presentation.solution.model.SelectRightAnswerOrAnswersUiModel
import java.util.Collections
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    getSolutionUseCase: GetSolutionUseCase, private val checkAnswerUseCase: CheckAnswerUseCase
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<HtmlItem>>(listOf())

    private var currentSolutionId: String = EMPTY_STRING

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()

    // TODO унести в дата сорс.
    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val mainModel: TestSolutionQueryModel = getSolutionUseCase.invoke(CURRENT_SOLUTION_ID)
            val questionList: List<QuestionModel> = mainModel.testModel.questionList.sortedBy {
                it.testQuestionType
            }
            currentSolutionId = mainModel.solutionId
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
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = checkAnswerUseCase(
                solutionId = questionAnswerWithErrorsUiModel.solutionId,
                questionId = questionAnswerWithErrorsUiModel.questionId,
                answerOrAnswers = answer
            )

            val currentList = uiItemsState.value.toMutableList()

            val newValue = AnswerUiModel(
                answer = answer,
                rightAnswer = questionAnswerWithErrorsUiModel.rightAnswer,
                answerValidationResultType = AnswerValidationResultType.getType(result.responseObject.resultType)
            )

            Collections.replaceAll(currentList, questionAnswerWithErrorsUiModel, newValue)
            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun rightAnswerClicked(rightAnswerModel: QuestionRightAnswerUiModel, answer: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = rightAnswerModel.questionId,
                answerOrAnswers = answer
            )

            val currentList = uiItemsState.value.toMutableList()
            val answers = rightAnswerModel.rightAnswers.joinToString(separator = "\n")
            val newValue = AnswerUiModel(
                answer = answer,
                rightAnswer = answers,
                answerValidationResultType = AnswerValidationResultType.getType(result.responseObject.resultType)
            )
            Collections.replaceAll(currentList, rightAnswerModel, newValue)
            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
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
        Log.d(TAG, questionMap.toString())
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

            val currentList = uiItemsState.value.toMutableList()
            val answers = selectRightAnswerOrAnswersUiModel.answers

            val newValue = selectRightAnswerOrAnswersUiModel.copy(
                answers = answers.map {
                    it.enabled = false
                    it
                },
                answerValidationResultType = AnswerValidationResultType.getType(result.responseObject.resultType)
            )
            Collections.replaceAll(currentList, selectRightAnswerOrAnswersUiModel, newValue)
            uiItemsState.update { currentList }

        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun detailedAnswerClicked(
        questionAnswerWithErrorsUiModel: QuestionDetailedAnswerUiModel, answer: String
    ) {

    }

    fun openYoutube(link: String) {

    }

    companion object {
        var TAG = this::class.java.name
        const val CURRENT_SOLUTION_ID = "897dbe82-3387-43ac-afd6-7d495114238f"
    }
}