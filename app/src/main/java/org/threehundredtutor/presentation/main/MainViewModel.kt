package org.threehundredtutor.presentation.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.domain.main.usecase.EnterGroupUseCase
import org.threehundredtutor.domain.main.usecase.GetCoursesUseCase
import org.threehundredtutor.domain.main.usecase.GetExtraButtonsUseCase
import org.threehundredtutor.domain.main.usecase.GetSubjectUseCase
import org.threehundredtutor.presentation.main.mapper.toCourseProgressUiModel
import org.threehundredtutor.presentation.main.mapper.toCourseUiModel
import org.threehundredtutor.presentation.main.mapper.toSubjectUiModel
import org.threehundredtutor.presentation.main.ui_models.ActivateKeyUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseLottieUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel
import org.threehundredtutor.presentation.main.ui_models.FooterContentUiItem
import org.threehundredtutor.presentation.main.ui_models.FooterUiItem
import org.threehundredtutor.presentation.main.ui_models.HeaderContentUiItem
import org.threehundredtutor.presentation.main.ui_models.HeaderUiItem
import org.threehundredtutor.presentation.main.ui_models.MainUiItem
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util.addQuotes
import org.threehundredtutor.ui_core.SnackBarType
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSubjectUseCase: GetSubjectUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getConfigUseCase: GetConfigUseCase,
    private val resourceProvider: ResourceProvider,
    private val enterGroupUseCase: EnterGroupUseCase,
    private val getExtraButtonsUseCase: GetExtraButtonsUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<MainUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val loadingState = MutableStateFlow(false)


    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()

    init {
        loadListData()
    }

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)

            val subjects = async {
                getSubjectUseCase.invoke().map { subjectModel ->
                    subjectModel.toSubjectUiModel()
                }
            }

            val courses =
                async {
                    getCoursesUseCase().map { progressModel: GroupWithCourseProgressModel ->
                        if (progressModel.useCourse) {
                            progressModel.toCourseProgressUiModel()
                        } else {
                            progressModel.toCourseUiModel()
                        }
                    }
                }

            val extraButtons = async { getExtraButtonsUseCase() }

            uiItemsState.update {
                buildUiItems(
                    courses = courses.await(),
                    subjects = subjects.await(),
                    extraButtons = extraButtons.await()
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    private fun buildUiItems(
        courses: List<MainUiItem>,
        subjects: List<SubjectUiModel>,
        extraButtons: List<ExtraButtonInfoModel>
    ): List<MainUiItem> = buildList {
        add(ActivateKeyUiItem)

        add(HeaderUiItem(resourceProvider.string(UiCoreStrings.my_course)))
        if (courses.isNotEmpty()) addAll(courses)
        else add(CourseLottieUiItem)
        add(FooterUiItem)

        if (subjects.isNotEmpty()) {
            add(HeaderUiItem(resourceProvider.string(UiCoreStrings.subject)))
            addAll(subjects)
            add(FooterUiItem)
        }

        if (extraButtons.isNotEmpty()) {
            add(HeaderContentUiItem)
            addAll(extraButtons)
            add(FooterContentUiItem)
        }
    }

    fun onSubjectClicked(subjectUiModel: SubjectUiModel) {
        uiEventState.tryEmit(UiEvent.NavigateToDetailedSubject(subjectInfo = subjectUiModel.subjectId to subjectUiModel.subjectName))
    }

    fun onCourseClicked(courseUiModel: CourseUiModel) {
        //TODO sprint4
    }

    fun onCourseProgressClicked(courseProgressUiModel: CourseProgressUiModel) {
        //TODO sprint4
    }

    fun onCourseLottieClicked() {
        //TODO /api/tutor/course-shop-window-detailed/default
    }

    fun onActivateKeyClicked(key: String) {
        viewModelScope.launchJob(tryBlock = {
            val result = enterGroupUseCase.invoke(key)
            if (result.succeeded) {
                uiEventState.tryEmit(
                    UiEvent.ShowSnack(
                        resourceProvider.string(
                            UiCoreStrings.activate_key_message,
                            result.groupName.addQuotes()
                        )
                    )
                )
                loadListData()
            } else {
                uiEventState.tryEmit(UiEvent.ShowSnack(result.errorMessage, SnackBarType.ERROR))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onExtraButtonClicked(link: String) {
        uiEventState.tryEmit(UiEvent.ShowDialogOpenLink(link))
    }

    fun onDialogOpenLinkPositiveClicked(link: String) {
        uiEventState.tryEmit(UiEvent.OpenLink(link = link, siteUrl = getConfigUseCase().siteUrl))
    }

    sealed interface UiEvent {
        data class ShowDialogOpenLink(val link: String) : UiEvent
        data class NavigateToDetailedSubject(val subjectInfo: Pair<String, String>) : UiEvent
        data class OpenLink(val link: String, val siteUrl: String) : UiEvent
        data class ShowSnack(
            val message: String,
            val snackBarType: SnackBarType = SnackBarType.SUCCESS
        ) : UiEvent
    }
}