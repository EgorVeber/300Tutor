package org.threehundredtutor.presentation.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.addQuotes
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.common.utils.SnackBarType
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.domain.main.usecase.EnterGroupUseCase
import org.threehundredtutor.domain.main.usecase.GetCoursesUseCase
import org.threehundredtutor.domain.main.usecase.GetExtraButtonsUseCase
import org.threehundredtutor.domain.main.usecase.GetSubjectUseCase
import org.threehundredtutor.domain.main.usecase.SearchTestUseCase
import org.threehundredtutor.presentation.main.mapper.toCourseProgressUiModel
import org.threehundredtutor.presentation.main.mapper.toCourseUiModel
import org.threehundredtutor.presentation.main.mapper.toSubjectTestUiModel
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
import org.threehundredtutor.presentation.main.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSubjectUseCase: GetSubjectUseCase,
    private val searchTestUseCase: SearchTestUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
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

        add(HeaderUiItem(resourceProvider.string(R.string.my_course)))
        if (courses.isNotEmpty()) addAll(courses)
        else add(CourseLottieUiItem)
        add(FooterUiItem)

        if (subjects.isNotEmpty()) {
            add(HeaderUiItem(resourceProvider.string(R.string.subject)))
            addAll(subjects)
            add(FooterUiItem)
        }

        if (extraButtons.isNotEmpty()) {
            add(HeaderContentUiItem)
            addAll(extraButtons)
            add(FooterContentUiItem)
        }
    }

    // TODO избавиться от этого кода в TutorAndroid-56
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

    fun onActivateKeyClicked(key: String) {
        viewModelScope.launchJob(tryBlock = {
            val result = enterGroupUseCase.invoke(key)
            if (result.succeeded) {
                uiEventState.tryEmit(
                    UiEvent.ShowSnack(
                        resourceProvider.string(
                            R.string.activate_key_message,
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

    fun onCourseClicked(courseUiModel: CourseUiModel) {

    }

    fun onCourseProgressClicked(courseProgressUiModel: CourseProgressUiModel) {

    }

    fun onExtraButtonClicked(link: String) {
        uiEventState.tryEmit(UiEvent.ShowDialogOpenLink(link))
    }

    fun onDialogOpenLinkPositiveClicked(link: String) {
        uiEventState.tryEmit(UiEvent.OpenLink(link))
    }

    fun onCourseLottieCliked() {
        //TODO /api/tutor/course-shop-window-detailed/default
    }

    sealed interface UiEvent {
        data class ShowDialogStartTest(val subjectTestUiModel: SubjectTestUiModel) : UiEvent
        data class ShowDialogOpenLink(val link: String) : UiEvent
        data class NavigateSolution(val subjectTestId: String) : UiEvent
        data class OpenLink(val link: String) : UiEvent
        data class ShowSnack(
            val message: String,
            val snackBarType: SnackBarType = SnackBarType.SUCCESS
        ) : UiEvent
    }
}