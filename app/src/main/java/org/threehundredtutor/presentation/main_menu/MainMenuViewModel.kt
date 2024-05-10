package org.threehundredtutor.presentation.main_menu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.presentation.main_menu.adapter.MainMenuUiItem
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    getAccountUseCase: GetAccountUseCase
) : BaseViewModel() {

    private val accountState = MutableStateFlow(AccountModel.EMPTY)
    private val uiItemsState = MutableStateFlow<List<MainMenuUiItem>>(listOf())

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

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun onMenuItemClicked(mainMenuItem: MainMenuUiItem) {

    }
}
