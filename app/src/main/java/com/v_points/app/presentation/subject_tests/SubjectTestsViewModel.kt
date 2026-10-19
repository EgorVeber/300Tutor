package com.v_points.app.presentation.subject_tests

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.v_points.app.domain.subject_tests.models.SearchTestInfoModel
import com.v_points.app.domain.subject_tests.models.SearchTestModel
import com.v_points.app.domain.subject_tests.usecase.SearchTestUseCase
import com.v_points.app.presentation.main.mapper.toSubjectTestUiModel
import com.v_points.app.presentation.subject_tests.ui_models.SubjectEmptyTestsUiItem
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestUiModel
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestsUiItem
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SubjectTestsViewModel @Inject constructor(
    private val subjectTestId: String,
    private val searchTestUseCase: SearchTestUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SubjectTestsUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val searchTestModel: SearchTestModel = searchTestUseCase(subjectTestId)
            val uiItems =
                searchTestModel.searchTestInfoModelList.map { searchTestInfoModel: SearchTestInfoModel ->
                    searchTestInfoModel.toSubjectTestUiModel(subjectTestId)
                }
            uiItemsState.update { uiItems.ifEmpty { listOf(SubjectEmptyTestsUiItem) } }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onTestClicked(subjectTestUiModel: SubjectTestUiModel) {
        uiEventState.tryEmit(
            UiEvent.NavigateToTest(
                subjectTestUiModel.subjectTestId,
                subjectTestUiModel.subjectTestName,
                subjectTestUiModel.questionsCount
            )
        )
    }

    sealed interface UiEvent {
        data class NavigateToTest(
            val subjectTestId: String,
            val subjectTestName: String,
            val questionsCount: Int
        ) : UiEvent
    }
}