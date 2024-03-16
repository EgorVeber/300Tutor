package org.threehundredtutor.presentation.test

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.domain.test.SearchSolutionUseCase
import org.threehundredtutor.presentation.solution_history.mapper.toSolutionHistoryUiModel
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiItem
import org.threehundredtutor.presentation.solution_history.models.StartTestUiModel
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class TestViewModel @Inject constructor(
    private val testBundleModel: TestBundleModel,
    private val searchSolutionUseCase: SearchSolutionUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SolutionHistoryUiItem>>(emptyList())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    private fun loadSolutions() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val (solutionHistoryItems, isAllFinished) = searchSolutionUseCase(
                testId = testBundleModel.testId
            )

            uiItemsState.update {
                buildUiItems(
                    solutionHistoryUiItems = solutionHistoryItems.map { solutionHistoryItemModel ->
                        solutionHistoryItemModel.toSolutionHistoryUiModel(
                            resourceProvider = resourceProvider,
                            questionsCount = testBundleModel.questionCount
                        )
                    },
                    isAllFinished = isAllFinished
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onViewInitiated() {
        loadSolutions()
    }

    fun onRefresh() {
        loadSolutions()
    }

    fun onSolutionHistoryClicked(solutionId: String) {
        uiEventState.tryEmit(UiEvent.NavigateToSolutionLoadSolution(solutionId = solutionId))
    }

    fun onStartTestClicked(testId: String) {
        uiEventState.tryEmit(UiEvent.NavigateToSolutionStartTest(testId = testId))
    }

    private fun buildUiItems(
        solutionHistoryUiItems: List<SolutionHistoryUiItem>,
        isAllFinished: Boolean,
    ): List<SolutionHistoryUiItem> = buildList {
        if (isAllFinished) add(
            StartTestUiModel(
                testId = testBundleModel.testId,
                nameTest = testBundleModel.testName,
                questionsCount = testBundleModel.questionCount
            )
        )
        addAll(solutionHistoryUiItems)
    }

    sealed interface UiEvent {
        @JvmInline
        value class NavigateToSolutionLoadSolution(val solutionId: String) : UiEvent

        @JvmInline
        value class NavigateToSolutionStartTest(val testId: String) : UiEvent
    }
}