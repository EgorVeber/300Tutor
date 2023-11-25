package org.threehundredtutor.presentation.solution_history

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.domain.solution_history.SearchSolutionUseCase
import org.threehundredtutor.domain.solution_history.SolutionHistoryFilter
import javax.inject.Inject

class SolutionHistoryViewModel @Inject constructor(
    private val searchSolutionUseCase: SearchSolutionUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private var currentFilter = SolutionHistoryFilter.ALL

    private val uiItemsState = MutableStateFlow<List<SolutionHistoryUiModel>>(listOf())
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
            val items =
                searchSolutionUseCase.invoke(force = force, solutionHistoryFilter = filter)
                    .map { solutionHistoryItemModel ->
                        solutionHistoryItemModel.toSolutionHistoryUiModel(resourceProvider)
                    }
            uiItemsState.update { items }
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

    fun onGoSolutionClickListener(solutionId: String) {
        uiEventState.tryEmit(UiEvent.ShowDialogSolution(solutionId))
    }

    fun onDialogOkClicked(solutionId: String) {
        uiEventState.tryEmit(UiEvent.NavigateToSolution(solutionId))
    }

    sealed interface UiEvent {
        data class ShowDialogSolution(val solutionId: String) : UiEvent
        data class NavigateToSolution(val solutionId: String) : UiEvent
    }
}