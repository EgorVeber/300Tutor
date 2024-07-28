package org.threehundredtutor.presentation.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.account.usecase.CreateLoginLinkResultUseCase
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.domain.main.usecase.EnterGroupUseCase
import org.threehundredtutor.domain.main.usecase.GetCoursesUseCase
import org.threehundredtutor.domain.main.usecase.GetExtraButtonsUseCase
import org.threehundredtutor.domain.main.usecase.GetSubjectUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.main.mapper.toCourseProgressUiModel
import org.threehundredtutor.presentation.main.mapper.toCourseUiModel
import org.threehundredtutor.presentation.main.mapper.toSubjectUiModel
import org.threehundredtutor.presentation.main.ui_models.ActivateKeyUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseLottieUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel
import org.threehundredtutor.presentation.main.ui_models.EmptyMainUiItem
import org.threehundredtutor.presentation.main.ui_models.FooterUiItem
import org.threehundredtutor.presentation.main.ui_models.HeaderUiItem
import org.threehundredtutor.presentation.main.ui_models.MainDividerUiItem
import org.threehundredtutor.presentation.main.ui_models.MainUiItem
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_core.SnackBarType
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSubjectUseCase: GetSubjectUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getConfigUseCase: GetConfigUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val resourceProvider: ResourceProvider,
    private val enterGroupUseCase: EnterGroupUseCase,
    private val getExtraButtonsUseCase: GetExtraButtonsUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
    private val solutionFactory: SolutionFactory, // TODO унести потом
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
            val settings = getSettingAppUseCase(false)

            val subjects = async {
                getSubjectUseCase.invoke().map { subjectModel ->
                    val path = if (subjectModel.iconModel.name.isEmpty()) "" else {
                        SolutionFactory.replaceUrl(
                            url = settings.publicImageUrlFormat,
                            fileId = subjectModel.iconModel.fileId.toString()
                        )
                    }
                    subjectModel.toSubjectUiModel(path)
                }
            }

            val studentId = async {
                getAccountUseCase(false).userId
            }

            val courses =
                async {
                    getCoursesUseCase(studentId.await()).map { progressModel: GroupWithCourseProgressModel ->
                        val path = if (progressModel.iconModel.name.isEmpty()) "" else {
                            SolutionFactory.replaceUrl(
                                url = settings.publicImageUrlFormat,
                                fileId = progressModel.iconModel.fileId.toString()
                            )
                        }

                        if (progressModel.useCourse) {
                            progressModel.toCourseProgressUiModel(path)
                        } else {
                            progressModel.toCourseUiModel(path)
                        }
                    }
                }

            uiItemsState.update {
                buildUiItems(
                    courses = courses.await(),
                    subjects = subjects.await(),
                    emptyCoursesImagePath = settings.imagesPack.error
                )
            }
        }, catchBlock =
        { throwable ->
            handleError(throwable)
            uiItemsState.update {
                listOf(EmptyMainUiItem(resourceProvider.string(UiCoreStrings.error_loading_common)))
            }
        }, finallyBlock =
        {
            loadingState.tryEmit(false)
        })
    }

    private fun buildUiItems(
        courses: List<MainUiItem>,
        subjects: List<SubjectUiModel>,
        emptyCoursesImagePath: String,
    ): List<MainUiItem> = buildList {
        add(HeaderUiItem(resourceProvider.string(UiCoreStrings.my_course)))
        if (courses.isNotEmpty()) addAll(courses)
        else add(CourseLottieUiItem(emptyCoursesImagePath))
        add(ActivateKeyUiItem(courses.isNotEmpty()))
        add(FooterUiItem)
        add(MainDividerUiItem)
        if (subjects.isNotEmpty()) {
            add(HeaderUiItem(resourceProvider.string(UiCoreStrings.subject)))
            addAll(subjects)
            add(FooterUiItem)
        }
    }

    fun onSubjectClicked(subjectUiModel: SubjectUiModel) {
        uiEventState.tryEmit(UiEvent.NavigateToDetailedSubject(subjectInfo = subjectUiModel.subjectId to subjectUiModel.subjectName))
    }

    fun onCourseClicked(courseUiModel: CourseUiModel) {
        //TODO sprint4
        createLoginLinkAndShowDialog(getLinkGroupById(courseUiModel.groupId))
    }

    fun onCourseProgressClicked(courseProgressUiModel: CourseProgressUiModel) {
        //TODO sprint4=
        createLoginLinkAndShowDialog(getLinkGroupById(courseProgressUiModel.groupId))
    }

    fun onCourseLottieClicked() {
        //TODO /api/tutor/course-shop-window-detailed/default
        createLoginLinkAndShowDialog(REDIRECT_LINK_COURSES)
    }

    private fun createLoginLinkAndShowDialog(link: String) {
        viewModelScope.launchJob(tryBlock = {
            val setting = getSettingAppUseCase(false)
            val result = createLoginLinkResultUseCase(setting.applicationUrl, link)
            if (result.isSucceeded) {
                uiEventState.tryEmit(
                    UiEvent.NavigateWebScreen(
                        urlAuthentication = result.urlAuthentication,
                        siteUrl = setting.applicationUrl,
                        schoolName = setting.applicationName
                    )
                )
            } else {
                uiEventState.tryEmit(
                    UiEvent.ShowSnack(
                        resourceProvider.string(UiCoreStrings.main_to_web_screen_link_failed),
                        SnackBarType.ERROR
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onActivateKeyClicked() {
        uiEventState.tryEmit(UiEvent.OpenActivateKeyDialog)
    }

    private fun getLinkGroupById(id: String) =
        REDIRECT_STUDENT_GROUP_COURSES.replace(ID_GROUP, id)

    fun onTickUpClicked() {
        createLoginLinkAndShowDialog(REDIRECT_LINK_COURSES)
    }

    fun onSuccessActivateKey() {
        loadListData()
    }

    fun onRefresh() {
        loadListData()
    }

    sealed interface UiEvent {
        data class NavigateToDetailedSubject(val subjectInfo: Pair<String, String>) : UiEvent

        data class NavigateWebScreen(
            val urlAuthentication: String,
            val schoolName: String,
            val siteUrl: String
        ) : UiEvent

        object OpenActivateKeyDialog : UiEvent

        data class ShowSnack(
            val message: String,
            val snackBarType: SnackBarType = SnackBarType.SUCCESS
        ) : UiEvent
    }

    companion object {
        const val REDIRECT_LINK_COURSES = "/courses"
        const val ID_GROUP = "{id}"
        const val REDIRECT_STUDENT_GROUP_COURSES = "/student/group/{id}"
    }
}