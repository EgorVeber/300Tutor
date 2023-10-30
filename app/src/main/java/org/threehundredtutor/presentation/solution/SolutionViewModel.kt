package org.threehundredtutor.presentation.solution

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.presentation.solution.html_helper.SolutionFactory
import org.threehundredtutor.presentation.solution.models.SolutionItem
import org.threehundredtutor.presentation.solution.models.answer.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.models.answer.AnswerXUiModel
import org.threehundredtutor.presentation.solution.models.answer.DetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.models.answer.ResultAnswerUiModel
import org.threehundredtutor.presentation.solution.models.answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.models.check.CheckButtonUiItem
import org.threehundredtutor.presentation.solution.models.check.ResultButtonUiItem
import java.util.Collections
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    solutionFactory: SolutionFactory,
    getSolutionUseCase: GetSolutionUseCase,
    private val checkAnswerUseCase: CheckAnswerUseCase,
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<SolutionItem>>(listOf())

    private var currentSolutionId: String = EMPTY_STRING

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()

    // TODO унести в дата сорс а может и нет.
    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val mainModel = getSolutionUseCase.invoke(CURRENT_SOLUTION_ID)
            currentSolutionId = mainModel.solutionId
            val uiItemList = solutionFactory.createSolution(mainModel.testModel.questionList)
            uiItemsState.update { uiItemList }

        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun answerWithErrorsClicked(
        answerWithErrorsUiModel: AnswerWithErrorsUiModel, answer: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val resultType = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = answerWithErrorsUiModel.questionId,
                answerOrAnswers = answer
            ).responseObject.resultType

            val currentList = uiItemsState.value.toMutableList()

            val newValue = ResultAnswerUiModel(
                answer = answer,
                rightAnswer = answerWithErrorsUiModel.rightAnswer,
                answerValidationResultType = AnswerValidationResultType.getType(resultType)
            )

            Collections.replaceAll(currentList, answerWithErrorsUiModel, newValue)
            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun rightAnswerClicked(rightAnswerModel: RightAnswerUiModel, answer: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val resultType = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = rightAnswerModel.questionId,
                answerOrAnswers = answer
            ).responseObject.resultType

            val currentList = uiItemsState.value.toMutableList()
            val answers = rightAnswerModel.rightAnswers.joinToString(separator = "\n")

            val newValue = ResultAnswerUiModel(
                answer = answer,
                rightAnswer = answers,
                answerValidationResultType = AnswerValidationResultType.getType(resultType)
            )
            Collections.replaceAll(currentList, rightAnswerModel, newValue)
            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun detailedAnswerClicked(detailedAnswerUiModel: DetailedAnswerUiModel, answer: String) {}

    fun openYoutube(link: String) {}

    fun itemChecked(questionId: String, answerText: String, checked: Boolean) {
        val answersList = questionMap.getOrPut(key = questionId, defaultValue = { emptyList() })
        if (checked) {
            questionMap[questionId] = answersList + listOf(answerText)
        } else {
            questionMap[questionId] = answersList - listOf(answerText).toSet()
        }

        val currentList = uiItemsState.value.toMutableList().map { solutionItem ->
            if (solutionItem is AnswerXUiModel && solutionItem.questionId == questionId && solutionItem.answer == answerText) {
                return@map solutionItem.copy(checked = checked)
            }
            solutionItem
        }

        uiItemsState.update { currentList }
    }

    fun checkButtonClicked(questionId: String) {
        val answerJoin =
            questionMap.getOrPut(questionId) { emptyList() }.joinToString(separator = ";")
        if (answerJoin.isEmpty()) return

        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val resultType = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = questionId,
                answerOrAnswers = answerJoin
            ).responseObject.resultType

            val currentList = uiItemsState.value.toMutableList().map { solutionItem ->
                if (solutionItem is AnswerXUiModel && solutionItem.questionId == questionId) {
                    return@map solutionItem.copy(enabled = false)
                }
                solutionItem
            }

            val oldItem = currentList.find { solutionItem ->
                solutionItem is CheckButtonUiItem && solutionItem.questionId == questionId
            }
            oldItem ?: return@launchJob
            val newItem = ResultButtonUiItem(AnswerValidationResultType.getType(resultType))
            Collections.replaceAll(currentList, oldItem, newItem)

            uiItemsState.update { currentList }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    companion object {
        var TAG = this::class.java.name
        const val CURRENT_SOLUTION_ID = "897dbe82-3387-43ac-afd6-7d495114238f"
    }
}