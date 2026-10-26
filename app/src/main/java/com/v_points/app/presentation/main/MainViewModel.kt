package com.v_points.app.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.main.ui_models.MainUiItem
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface MainState {
    val items: List<MainMenuUiModel>
}

class MutableMainState : MainState {
    override var items: List<MainMenuUiModel> by mutableStateOf(listOf())
}


class MainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val uiItemsState = MutableMainState()
    private val usersState = MutableStateFlow<List<String>>(listOf())
    private val loadingState = MutableStateFlow(false)

    fun getMainState():MainState = uiItemsState
    fun getLoadingState() = loadingState.asStateFlow()
    fun getUsersStateFlow() = usersState.asStateFlow()

    init {
        loadListData()
    }

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            uiItemsState.items = getMenuItems()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    private fun getMenuItems() =
        buildList {
            add(
                MainMenuUiModel.Users(
                    title = resourceProvider.string(UiCoreStrings.v_participants),
                    subtitle = null,
                    iconRes = null
                )
            )
            add(
                MainMenuUiModel.Games(
                    title = resourceProvider.string(UiCoreStrings.v_games),
                    subtitle = null,
                    iconRes = null
                )
            )
            add(
                MainMenuUiModel.Profile(
                    title = resourceProvider.string(UiCoreStrings.v_profile),
                    subtitle = null,
                    iconRes = null
                )
            )
        }
}