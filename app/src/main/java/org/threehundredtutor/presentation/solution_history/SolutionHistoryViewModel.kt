package org.threehundredtutor.presentation.solution_history

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.domain.solution_history.SearchSolutionFilteredUseCase
import org.threehundredtutor.domain.solution_history.SolutionHistoryFilter
import org.threehundredtutor.presentation.solution_history.mapper.toSolutionHistoryUiModel
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiItem
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SolutionHistoryViewModel @Inject constructor(
    private val searchSolutionFilteredUseCase: SearchSolutionFilteredUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private var currentFilter = SolutionHistoryFilter.ALL

    private val uiItemsState = MutableStateFlow<List<SolutionHistoryUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val loadingState = MutableStateFlow(false)

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()

    init {
        searchSolution(force = true, filter = currentFilter)
    }

    private fun searchSolution(force: Boolean, filter: SolutionHistoryFilter) {
        viewModelScope.launchJob(tryBlock = {
            if (force) loadingState.update { true }
            val solutionHistoryItems =
                searchSolutionFilteredUseCase.invoke(
                    force = force,
                    solutionHistoryFilter = filter,
                )

            val uiItems = solutionHistoryItems.map { solutionHistoryItemModel ->
                solutionHistoryItemModel.toSolutionHistoryUiModel(
                    resourceProvider = resourceProvider,
                    questionsCount = DEFAULT_NOT_VALID_VALUE_INT
                )
            }

            uiItemsState.update { uiItems }
        }, catchBlock = { throwable ->
            //TODO TutorAndroid-44
            handleError(throwable)
            loadingState.update { false }
        }, finallyBlock = {
            if (force) loadingState.update { false }
        })
    }

    fun onCompletedChipClicked() {
        currentFilter = SolutionHistoryFilter.COMPLETED
        searchSolution(force = false, filter = currentFilter)
    }

    fun onNotCompletedChipClicked() {
        currentFilter = SolutionHistoryFilter.NOT_COMPLETED
        searchSolution(force = false, filter = currentFilter)
    }

    fun onAllChipClicked() {
        currentFilter = SolutionHistoryFilter.ALL
        searchSolution(force = false, filter = currentFilter)
    }

    fun onRefresh() {
        searchSolution(force = true, filter = currentFilter)
    }

    fun onSolutionHistoryClicked(solutionId: String, isFinished: Boolean) {
        if (isFinished) {
            uiEventState.tryEmit(UiEvent.ShowDialogGoSolution(solutionId))
        } else {
            uiEventState.tryEmit(UiEvent.ShowDialogContinueSolution(solutionId))
        }
    }

    fun onDialogOkClicked(solutionId: String) {
        uiEventState.tryEmit(UiEvent.NavigateToSolution(solutionId))
    }

    sealed interface UiEvent {
        data class ShowDialogGoSolution(val solutionId: String) : UiEvent
        data class ShowDialogContinueSolution(val solutionId: String) : UiEvent
        data class NavigateToSolution(val solutionId: String) : UiEvent
    }
}