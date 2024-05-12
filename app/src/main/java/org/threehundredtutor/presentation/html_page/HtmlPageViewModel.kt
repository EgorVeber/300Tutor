package org.threehundredtutor.presentation.html_page

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.solution.models.test_model.HtmlPageTestType
import org.threehundredtutor.domain.subject_workspace.FindDirectoryByIdUseCase
import org.threehundredtutor.domain.subject_workspace.GetWorkSpaceIdUseCase
import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.domain.subject_workspace.models.HtmlPageModel
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.html_page.adapter.HtmlPageEmptyUiItem
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class HtmlPageViewModel @Inject constructor(
    private val directoryId: String,
    private val findDirectoryByIdUseCase: FindDirectoryByIdUseCase,
    private val solutionFactory: SolutionFactory,
    private val resourceProvider: ResourceProvider,
    private val getConfigUseCase: GetConfigUseCase,
    private val getWorkSpaceIdUseCase: GetWorkSpaceIdUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val messagesStream = MutableStateFlow(EMPTY_STRING)
    private val uiEventState = SingleSharedFlow<UiEvent>()

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()
    fun getMessagesStream() = messagesStream.asStateFlow()

    init {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val directoryModel: DirectoryModel? = findDirectoryByIdUseCase(directoryId)
            if (directoryModel != null && directoryModel.htmlPageModel != HtmlPageModel.EMPTY && directoryModel.htmlPageModel.html.isNotEmpty()) {
                val uiItems = solutionFactory.createHtmlPage(
                    htmlString = directoryModel.htmlPageModel.html,
                    staticUrl = getConfigUseCase().staticMediumUrl,
                    directoryModel = directoryModel,
                )
                uiItemsState.update { uiItems }
            } else {
                uiItemsState.update { listOf(HtmlPageEmptyUiItem) }
            }
        }, finallyBlock = {
            loadingState.update { false }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onImageClicked(imageId: String) {
        if (imageId.isNotEmpty()) uiEventState.tryEmit(
            UiEvent.NavigatePhotoDetailed(
                imageId = imageId,
                staticOriginalUrl = getConfigUseCase().staticOriginalUrl
            )
        )
    }

    fun onYoutubeClicked(link: String) {
        uiEventState.tryEmit(UiEvent.OpenYoutube(link))
    }

    fun onFirstStartTestClicked() {
        uiEventState.tryEmit(
            UiEvent.NavigateToSolution(
                directoryId = directoryId,
                workspaceId = getWorkSpaceIdUseCase(),
                htmlPageTestType = HtmlPageTestType.FIRST_PATH
            )
        )
    }

    fun onSecondStartTestClicked() {
        uiEventState.tryEmit(
            UiEvent.NavigateToSolution(
                directoryId = directoryId,
                workspaceId = getWorkSpaceIdUseCase(),
                htmlPageTestType = HtmlPageTestType.SECOND_PATH
            )
        )
    }

    fun onFullStartTestClicked() {
        uiEventState.tryEmit(
            UiEvent.NavigateToSolution(
                directoryId = directoryId,
                workspaceId = getWorkSpaceIdUseCase(),
                htmlPageTestType = HtmlPageTestType.FULL_TEST
            )
        )
    }

    sealed interface UiEvent {

        data class NavigateToSolution(
            val directoryId: String,
            val workspaceId: String,
            val htmlPageTestType: HtmlPageTestType
        ) : UiEvent

        data class NavigatePhotoDetailed(val imageId: String, val staticOriginalUrl: String) :
            UiEvent

        @JvmInline
        value class OpenYoutube(val link: String) : UiEvent
    }
}