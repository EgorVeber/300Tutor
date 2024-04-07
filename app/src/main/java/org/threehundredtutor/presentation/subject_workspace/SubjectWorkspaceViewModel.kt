package org.threehundredtutor.presentation.subject_workspace

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.subject_workspace.FindDirectoryByIdUseCase
import org.threehundredtutor.domain.subject_workspace.GetWorkSpaceUseCase
import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.domain.subject_workspace.models.HtmlPageModel
import org.threehundredtutor.presentation.subject_workspace.adapter.EmptyDirectoryUiItem
import org.threehundredtutor.presentation.subject_workspace.adapter.WorkspaceDirectoryUiItem
import org.threehundredtutor.presentation.subject_workspace.mapper.toDirectoryUiModel
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SubjectWorkspaceViewModel @Inject constructor(
    private val subjectWorkspaceBundleModel: SubjectWorkspaceBundleModel,
    private val getWorkSpaceUseCase: GetWorkSpaceUseCase,
    private val findDirectoryByIdUseCase: FindDirectoryByIdUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<WorkspaceDirectoryUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            if (subjectWorkspaceBundleModel.workspaceId.isNotEmpty()) {
                val workspaceHtmlThreeModel =
                    getWorkSpaceUseCase.invoke(workspaceId = subjectWorkspaceBundleModel.workspaceId)
                val uiItems =
                    workspaceHtmlThreeModel.directoryModel.childDirectoriesList.map { directoryModel ->
                        directoryModel.toDirectoryUiModel()
                    }
                uiItemsState.update { uiItems }
            } else {
                val cacheDirectoryModel: DirectoryModel? =
                    findDirectoryByIdUseCase(subjectWorkspaceBundleModel.directoryId)
                if (cacheDirectoryModel != null && cacheDirectoryModel.childDirectoriesList.isNotEmpty()) {
                    val uiItems = cacheDirectoryModel.childDirectoriesList.map { directoryModel ->
                        directoryModel.toDirectoryUiModel()
                    }
                    uiItemsState.update { uiItems }
                } else {
                    uiItemsState.update { listOf(EmptyDirectoryUiItem) }
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onDirectoryClicked(directoryId: String) {
        val cacheDirectoryModel = findDirectoryByIdUseCase(directoryId)
        if (cacheDirectoryModel != null) {
            if (cacheDirectoryModel.htmlPageModel != HtmlPageModel.EMPTY) {
                uiEventState.tryEmit(
                    UiEvent.NavigateToHtmlPage(
                        directoryId = directoryId,
                        subTitleDirectoryName = cacheDirectoryModel.directoryName,
                    )
                )
            } else {
                uiEventState.tryEmit(
                    UiEvent.NavigateToDirectory(
                        directoryId = directoryId,
                        subTitleDirectoryName = cacheDirectoryModel.directoryName,
                        subTitleWorkSpaceOrDirectory = subjectWorkspaceBundleModel.subTitleWorkSpaceOrDirectory
                    )
                )
            }
        } else {
            uiEventState.tryEmit(UiEvent.ShowErrorFind)
        }
    }

    sealed interface UiEvent {
        data class NavigateToDirectory(
            val directoryId: String,
            val subTitleDirectoryName: String,
            val subTitleWorkSpaceOrDirectory: String
        ) : UiEvent

        data class NavigateToHtmlPage(
            val directoryId: String,
            val subTitleDirectoryName: String,
        ) : UiEvent

        object ShowErrorFind : UiEvent
    }
}