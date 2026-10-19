package com.v_points.app.presentation.subjetc_start_test

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.v_points.app.domain.test.SearchSolutionUseCase
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.solution_history.mapper.toSolutionHistoryUiModel
import com.v_points.app.presentation.solution_history.models.SolutionHistoryUiItem
import com.v_points.app.presentation.solution_history.models.StartTestUiModel
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SubjectStartTestViewModel @Inject constructor(
    private val subjectStartTestBundleModel: SubjectStartTestBundleModel,
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
                testId = subjectStartTestBundleModel.testId
            )

            uiItemsState.update {
                buildUiItems(
                    solutionHistoryUiItems = solutionHistoryItems.map { solutionHistoryItemModel ->
                        solutionHistoryItemModel.toSolutionHistoryUiModel(
                            resourceProvider = resourceProvider,
                            questionsCount = subjectStartTestBundleModel.questionCount
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
                testId = subjectStartTestBundleModel.testId,
                nameTest = subjectStartTestBundleModel.testName,
                questionsCount = subjectStartTestBundleModel.questionCount
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