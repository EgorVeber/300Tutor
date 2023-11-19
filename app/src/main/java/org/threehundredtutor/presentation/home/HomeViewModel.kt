package org.threehundredtutor.presentation.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.subject.usecase.GetSubjectUseCase
import org.threehundredtutor.domain.subject.usecase.SearchTestUseCase
import org.threehundredtutor.presentation.home.mapper.toSubjectTestUiModel
import org.threehundredtutor.presentation.home.ui_models.SubjectHeaderUiItem
import org.threehundredtutor.presentation.home.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.home.ui_models.SubjectUiItem
import org.threehundredtutor.presentation.home.ui_models.SubjectUiModel
import org.threehundredtutor.presentation.home.ui_models.toSubjectUiModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSubjectUseCase: GetSubjectUseCase,
    private val searchTestUseCase: SearchTestUseCase
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SubjectUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()

    // TODO TutorAndroid-37 
    init {
        viewModelScope.launchJob(tryBlock = {
            val subjects = getSubjectUseCase.invoke().map { subjectModel ->
                subjectModel.toSubjectUiModel()
            }
            uiItemsState.update { listOf(SubjectHeaderUiItem) + subjects }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    //TODO Надо класть в кешь
    fun onSubjectClicked(subjectUiModel: SubjectUiModel) {
        viewModelScope.launchJob(tryBlock = {
            val searchTestModel = searchTestUseCase.invoke(subjectUiModel.subjectId)
            val testInfoList = searchTestModel.searchTestInfoModelList
            val currentList = uiItemsState.value.toMutableList()
            if (subjectUiModel.checked) {
                val currentIndex = currentList.indexOf(subjectUiModel)
                if (currentIndex == -1) return@launchJob

                if (currentList.size >= currentIndex + 1) {
                    currentList.addAll(currentIndex + 1, testInfoList.map { searchTestInfoModel ->
                        searchTestInfoModel.toSubjectTestUiModel(subjectUiModel.subjectId)
                    })

                    uiItemsState.update {
                        currentList.map { subjectUiItem ->
                            if (subjectUiItem is SubjectUiModel && subjectUiItem.subjectId == subjectUiModel.subjectId) {
                                subjectUiItem.copy(checked = false)
                            } else {
                                subjectUiItem
                            }
                        }
                    }
                }
            } else {
                currentList.removeAll(currentList.filter {
                    it is SubjectTestUiModel && it.subjectId == subjectUiModel.subjectId
                })
                uiItemsState.update {
                    currentList.map { subjectUiItem ->
                        if (subjectUiItem is SubjectUiModel && subjectUiItem.subjectId == subjectUiModel.subjectId) {
                            subjectUiItem.copy(checked = true)
                        } else {
                            subjectUiItem
                        }
                    }
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onSubjectTestClicked(subjectTestUiModel: SubjectTestUiModel) {
        uiEventState.tryEmit(UiEvent.ShowDialogStartTest(subjectTestUiModel))
    }

    fun onDialogStartTestPositiveClicked(subjectId: String) {
        uiEventState.tryEmit(UiEvent.NavigateSolution(subjectId))
    }

    sealed interface UiEvent {
        data class ShowDialogStartTest(val subjectTestUiModel: SubjectTestUiModel) : UiEvent
        data class NavigateSolution(val subjectTestId: String) : UiEvent
    }
}
