package org.threehundredtutor.presentation.subject_tests

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.subject_tests.models.SearchTestModel
import org.threehundredtutor.domain.subject_tests.usecase.SearchTestUseCase
import org.threehundredtutor.presentation.main.mapper.toSubjectTestUiModel
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestsUiItem
import javax.inject.Inject

class SubjectTestsViewModel @Inject constructor(
    private val subjectTestId: String,
    private val searchTestUseCase: SearchTestUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SubjectTestsUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val errorEvent = SingleSharedFlow<String>()

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()
    fun getErrorEventState() = errorEvent.asSharedFlow()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val searchTestModel: SearchTestModel = searchTestUseCase(subjectTestId)
            val uiItems = searchTestModel.searchTestInfoModelList.map { searchTestInfoModel ->
                searchTestInfoModel.toSubjectTestUiModel(subjectTestId)
            }
            uiItemsState.update { uiItems }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onTestClicked(subjectTestUiModel: SubjectTestUiModel) {
        // TutorAndroid-61
    }

    sealed interface UiEvent {
        data class NavigateToTest(
            val menuItem: SubjectMenuItemUiModel,
            val title: String
        ) : UiEvent
    }
}