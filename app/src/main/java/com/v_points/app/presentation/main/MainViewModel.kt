package com.v_points.app.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.domain.account.usecase.CreateLoginLinkResultUseCase
import com.v_points.app.domain.account.usecase.GetAccountUseCase
import com.v_points.app.domain.main.usecase.CreateTelegramLinkUseCase
import com.v_points.app.domain.main.usecase.GetCoursesUseCase
import com.v_points.app.domain.main.usecase.GetSubjectUseCase
import com.v_points.app.domain.main.usecase.GetTelegramDataUseCase
import com.v_points.app.domain.settings_app.GetSettingAppUseCase
import com.v_points.app.presentation.account.AccountViewModel
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.main.ui_models.ActivateKeyUiItem
import com.v_points.app.presentation.main.ui_models.CourseLottieUiItem
import com.v_points.app.presentation.main.ui_models.CourseProgressUiModel
import com.v_points.app.presentation.main.ui_models.CourseUiModel
import com.v_points.app.presentation.main.ui_models.EmptyMainUiItem
import com.v_points.app.presentation.main.ui_models.FooterUiItem
import com.v_points.app.presentation.main.ui_models.HeaderUiItem
import com.v_points.app.presentation.main.ui_models.MainDividerUiItem
import com.v_points.app.presentation.main.ui_models.MainUiItem
import com.v_points.app.presentation.main.ui_models.SubjectUiModel
import com.v_points.app.presentation.main.ui_models.TelegramBindUiItem
import com.v_points.app.presentation.main.ui_models.TelegramOpenUiItem
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.SnackBarType
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.time.delay
import java.util.Collections
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSubjectUseCase: GetSubjectUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val resourceProvider: ResourceProvider,
    private val getAccountUseCase: GetAccountUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
    private val getTelegramDataUseCase: GetTelegramDataUseCase,
    private val createTelegramLinkUseCase: CreateTelegramLinkUseCase,
) : BaseViewModel() {

    private val db by lazy { FirebaseFirestore.getInstance() }

    private val uiItemsState = MutableStateFlow<List<MainUiItem>>(listOf())
    private val usersState = MutableStateFlow<List<String>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val loadingState = MutableStateFlow(false)

    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()
    fun getUsersStateFlow() = usersState.asStateFlow()

    init {
        loadListData()
    }

    data class User(
        val id: String = "",
        val v_name: String = "",
        val v_lastName: String = "",
        val v_role_type: String = "",
    )

    fun DocumentSnapshot.toUser(): User? {
        val user = toObject(User::class.java) ?: return null
        return user.copy(id = id)   // подставляем ID документа
    }

    private suspend  fun getUsers(): List<User> {
        return FirebaseFirestore.getInstance()
            .collection("v_users").get().await()
            .documents
            .mapNotNull { it.toUser() }
    }

    private fun loadListData() {
        viewModelScope.launchJob(
            tryBlock = {
                loadingState.tryEmit(true)

                val users1 = getUsers()
                val users = users1.map { it.v_name + it.v_lastName[0] }

                users1.map {
                    Log.d("Users",it.v_name + " "+ it.v_lastName)
                }


                usersState.update { users }
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

    suspend fun addUsers(users: List<Triple<String, String, String>>) {
        try {
            // Добавляем каждый Triple как отдельный документ
            users.forEach { (name, lastName, role) ->
                val user = hashMapOf(
                    "v_name" to name,
                    "v_lastName" to lastName,
                    "v_roleType" to role
                )
                db.collection("v_users").add(user).await()
            }
            Log.d("Firestore", "Добавлено пользователей: ${users.size}")
        } catch (e: Exception) {
            Log.w("Firestore", "Ошибка добавления", e)
        }
    }

    suspend fun addRoles(users: List<String>) {
        try {
            // Добавляем каждый Triple как отдельный документ
            users.forEach { name ->
                val user = hashMapOf(
                    "v_role_type" to name
                )
                db.collection("v_roles").add(user).await()
            }
            Log.d("Firestore", "Добавлено пользователей: ${users.size}")
        } catch (e: Exception) {
            Log.w("Firestore", "Ошибка добавления", e)
        }
    }

    suspend fun addStats(users: List<String>) {
        try {
            // Добавляем каждый Triple как отдельный документ
            users.forEach { name ->
                val user = hashMapOf(
                    "v_stat" to name
                )
                db.collection("v_stats").add(user).await()
            }
            Log.d("Firestore", "Добавлено пользователей: ${users.size}")
        } catch (e: Exception) {
            Log.w("Firestore", "Ошибка добавления", e)
        }
    }


    suspend fun migrateRoles() {
        val db = FirebaseFirestore.getInstance()

        try {
            // 1. Загружаем роли: строим карту "значение v_role_type -> ID документа"
            val rolesSnapshot = db.collection("v_roles").get().await()
            val valueToRoleId = rolesSnapshot.documents.associate { doc ->
                (doc.getString("v_role_type") ?: "").trim() to doc.id
            }
            Log.d("Migration", "Загружено ролей: ${valueToRoleId.size}")
            Log.d("Migration", "Карта ролей: $valueToRoleId")

            // 2. Загружаем пользователей
            val usersSnapshot = db.collection("v_users").get().await()
            Log.d("Migration", "Загружено пользователей: ${usersSnapshot.size()}")

            // 3. Обновляем каждого пользователя
            val batch = db.batch()
            var updated = 0
            var skipped = 0

            usersSnapshot.documents.forEach { userDoc ->
                val oldRole = userDoc.getString("v_roleType")  // старое поле в v_users
                val roleId = valueToRoleId[oldRole?.trim()]

                if (roleId != null) {
                    batch.update(
                        userDoc.reference,
                        mapOf(
                            "v_roleType" to FieldValue.delete(),  // удаляем старое поле
                            "v_role_type" to roleId               // создаём новое с ID роли
                        )
                    )
                    updated++
                } else {
                    Log.w("Migration", "Не найдена роль для '${oldRole}' у ${userDoc.id}")
                    skipped++
                }
            }

            batch.commit().await()
            Log.d("Migration", "Обновлено: $updated, пропущено: $skipped")

        } catch (e: Exception) {
            Log.w("Migration", "Ошибка миграции", e)
        }
    }

    private fun buildUiItems(
        courses: List<MainUiItem>,
        subjects: List<SubjectUiModel>,
        emptyCoursesImagePath: String,
        hasTelegramBind: Boolean
    ): List<MainUiItem> = buildList {
        if (hasTelegramBind) add(TelegramBindUiItem)
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

    fun onTelegramClicked() {
        viewModelScope.launchJob(tryBlock = {
            val result = createTelegramLinkUseCase()
            if (result.isSucceeded) {
                uiEventState.tryEmit(
                    UiEvent.CopyLinkKeyboard(
                        link = result.command
                    )
                )

                val botWebButtonText =
                    getSettingAppUseCase(false).telegramBotSettingsModel.toBotWebButtonText

                val uiList = uiItemsState.value.toMutableList()
                Collections.replaceAll(
                    /* list = */ uiList,
                    /* oldVal = */
                    TelegramBindUiItem,
                    /* newVal = */
                    TelegramOpenUiItem(
                        resourceProvider.string(
                            UiCoreStrings.main_telegram_open,
                            botWebButtonText
                        )
                    )
                )
                uiItemsState.update { uiList }


            } else {
                uiEventState.tryEmit(
                    UiEvent.ShowSnack(
                        result.message,
                        SnackBarType.ERROR
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onOpenTelegramClicked() {
        viewModelScope.launchJob(tryBlock = {
            val botName = getSettingAppUseCase(false).telegramBotSettingsModel.botName
            uiEventState.tryEmit(
                UiEvent.OpenTelegram(
                    telegramBotUrl = AccountViewModel.TELEGRAM_DOMAIN + botName,
                    telegramBotName = botName
                )
            )
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    sealed interface UiEvent {
        data class NavigateToDetailedSubject(val subjectInfo: Pair<String, String>) : UiEvent

        data class NavigateWebScreen(
            val urlAuthentication: String,
            val schoolName: String,
            val siteUrl: String
        ) : UiEvent

        object OpenActivateKeyDialog : UiEvent

        @JvmInline
        value class CopyLinkKeyboard(val link: String) : UiEvent

        data class OpenTelegram(val telegramBotUrl: String, val telegramBotName: String) : UiEvent

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