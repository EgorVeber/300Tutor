package org.threehundredtutor.presentation.subject_detailed

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.subject_detailed.GetMenuSubjectConfigUseCase
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType.MENU_ITEM
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType.SUBJECT_TEST_LINK
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType.UNKNOWN
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType.WORK_SPACE_LINK
import org.threehundredtutor.presentation.subject_detailed.mappers.toSubjectDetailedMenuItemUiModel
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectDetailedUiItem
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class SubjectDetailedViewModel @Inject constructor(
    private val subjectDetailedBundleModel: SubjectDetailedBundleModel,
    private val getMenuSubjectConfigUseCase: GetMenuSubjectConfigUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SubjectDetailedUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            uiItemsState.update {
                if (subjectDetailedBundleModel.subjectId.isNotEmpty()) {
                    getMenuSubjectConfigUseCase(subjectDetailedBundleModel.subjectId).toSubjectDetailedMenuItemUiModel().subjectMenuItems
                } else {
                    subjectDetailedBundleModel.menuItem.subjectMenuItems
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onMenuItemClicked(subjectDetailedMenuItemUiModel: SubjectMenuItemUiModel) {
        when (subjectDetailedMenuItemUiModel.type) {
            MENU_ITEM -> {
                uiEventState.tryEmit(
                    UiEvent.NavigateToMenuItem(
                        menuItem = subjectDetailedMenuItemUiModel,
                        title = subjectDetailedMenuItemUiModel.text
                    )
                )
            }

            WORK_SPACE_LINK -> {

                uiEventState.tryEmit(
                    UiEvent.NavigateToWorkspace(
                        subjectName = subjectDetailedBundleModel.subjectName,
                        workspaceName = subjectDetailedMenuItemUiModel.text,
                        workspaceId = subjectDetailedMenuItemUiModel.workSpaceId
                    )
                )
            }

            SUBJECT_TEST_LINK -> {
                uiEventState.tryEmit(
                    UiEvent.NavigateToSubjectTests(
                        subjectName = subjectDetailedBundleModel.subjectName,
                        subjectMenuItemName = subjectDetailedMenuItemUiModel.text,
                        subjectId = subjectDetailedBundleModel.subjectId
                    )
                )
            }

            UNKNOWN -> {}
        }
    }

    sealed interface UiEvent {
        data class NavigateToMenuItem(
            val menuItem: SubjectMenuItemUiModel,
            val title: String
        ) : UiEvent

        data class NavigateToSubjectTests(
            val subjectName: String,
            val subjectMenuItemName: String,
            val subjectId: String
        ) : UiEvent

        data class NavigateToWorkspace(
            val subjectName: String,
            val workspaceName: String,
            val workspaceId: String
        ) : UiEvent
    }
}