package org.threehundredtutor.presentation.main_menu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.models.CreateLoginLinkResultModel
import org.threehundredtutor.domain.account.usecase.CreateLoginLinkResultUseCase
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.domain.settings_app.SettingsAppModel
import org.threehundredtutor.presentation.main_menu.adapter.MainMenuUiItem
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    getAccountUseCase: GetAccountUseCase,
    private val createLoginLinkResultUseCase: CreateLoginLinkResultUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase
) : BaseViewModel() {

    private val accountState = MutableStateFlow(AccountModel.EMPTY)
    private val uiItemsState = MutableStateFlow<List<MainMenuUiItem>>(listOf())
    private val uiAction = SingleSharedFlow<UiAction>()

    init {
        buildMainMenuItems()
        viewModelScope.launchJob(tryBlock = {
            accountState.value = getAccountUseCase.invoke(false)
        }, catchBlock = {
            handleError(it)
        })
    }

    private fun buildMainMenuItems() {
        uiItemsState.update {
            MainMenuItem.values().map { mainMenuItem ->
                MainMenuUiItem(mainMenuItem)
            }
        }
    }

    fun getAccountStateFlow(): Flow<AccountModel> = accountState
    fun getUiAction(): Flow<UiAction> = uiAction

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()

    fun onMenuItemClicked(mainMenuItem: MainMenuUiItem) {
        viewModelScope.launchJob(tryBlock = {
            val config = getSettingAppUseCase(false)
            when (mainMenuItem.mainMenuItem) {
                MainMenuItem.SCHEDULE -> {
                    val result = createLoginLinkResultUseCase.invoke(
                        config.applicationUrl,
                        REDIRECT_STUDENT_SCHEDULES
                    )
                    handeOpenSite(result, config)
                }

                MainMenuItem.NOTES -> {
                    val result = createLoginLinkResultUseCase.invoke(
                        config.applicationUrl,
                        REDIRECT_STUDENT_MATERIALS
                    )
                    handeOpenSite(result, config)
                }

                MainMenuItem.FAVORITES_QUESTION -> {
                    uiAction.tryEmit(UiAction.NavigateFavoritesScreen)
                }
            }

        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    private fun handeOpenSite(result: CreateLoginLinkResultModel, settings: SettingsAppModel) {
        if (result.isSucceeded) {
            uiAction.tryEmit(
                UiAction.NavigateWebScreen(
                    urlAuthentication = result.urlAuthentication,
                    schoolName = settings.applicationName,
                    siteUrl = settings.applicationUrl
                )
            )
        } else {
            uiAction.tryEmit(
                UiAction.NavigateWebScreen(
                    urlAuthentication = settings.applicationUrl, // TODO ПРООВЕРИТЬ ПОТОМ
                    schoolName = settings.applicationName,
                    siteUrl = settings.applicationUrl
                )
            )
        }
    }


    sealed interface UiAction {
        data class NavigateWebScreen(
            val urlAuthentication: String,
            val schoolName: String,
            val siteUrl: String
        ) : UiAction

        object NavigateFavoritesScreen : UiAction
    }

    companion object {
        const val REDIRECT_STUDENT_SCHEDULES = "/student/schedules"
        const val REDIRECT_STUDENT_MATERIALS = "/student/materials"
    }
}