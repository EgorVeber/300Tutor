package com.v_points.app.vpoint.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.flow.SingleSharedFlow
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.ui_core.components.game_day.GameDayUiModel
import com.v_points.app.ui_core.components.menu_item.MainMenuUiModel
import com.v_points.app.vpoint.fiersote.data.repository.GameDaysRepository
import com.v_points.app.vpoint.fiersote.data.repository.GymsRepository
import com.v_points.app.vpoint.fiersote.domain.GameDayModel
import com.v_points.app.vpoint.main.mappers.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val gameDaysRepository: GameDaysRepository,
    private val gymsRepository: GymsRepository,
) : BaseViewModel() {

    private val uiItemsState = MutableMainState()
    private val usersState = MutableStateFlow<List<String>>(listOf())
    private val loadingState = MutableStateFlow(false)

    private val navigateAction = SingleSharedFlow<MainScreenNavigateAction>()
    fun getNavigateAction(): Flow<MainScreenNavigateAction> = navigateAction

    fun getMainState(): MainState = uiItemsState
    fun getLoadingState() = loadingState.asStateFlow()
    fun getUsersStateFlow() = usersState.asStateFlow()

    init {
        loadListData()
    }

    private val userId = "YdjFrfak3zSt7NsOGgZr"

    private fun loadListData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)
            val gameDays: List<GameDayModel> = gameDaysRepository.getGameDaysWithDetails(userId)
            val gyms: Map<String, String> = gymsRepository.getGyms().associate { it.id to it.name }
            uiItemsState.items = gameDays.map {
                it.toUiModel(gyms)
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    suspend fun migrateAddGameDayIdToGames() {
        val db = FirebaseFirestore.getInstance()

        try {
            // 1. Получаем ВСЕ игры из всех подколлекций "games"
            val gamesSnapshot = db.collectionGroup("games").get().await()
            Log.d("Migration", "Найдено игр для обновления: ${gamesSnapshot.size()}")

            if (gamesSnapshot.isEmpty) {
                Log.d("Migration", "Игр не найдено. Нечего обновлять.")
                return
            }

            val batch = db.batch()
            var updatedCount = 0

            // 2. Проходимся по каждой игре
            for (gameDoc in gamesSnapshot.documents) {
                // Извлекаем ID дня из пути документа
                // Путь: gameDays/{dayId}/games/{gameId}
                // parent = games, parent.parent = gameDay
                val dayId = gameDoc.reference.parent.parent?.id

                if (dayId != null) {
                    // Обновляем документ, добавляя поле gameDayId
                    batch.update(gameDoc.reference, "gameDayId", dayId)
                    updatedCount++
                } else {
                    Log.w("Migration", "Не удалось определить dayId для игры ${gameDoc.id}")
                }
            }

            // 3. Применяем изменения
            batch.commit().await()
            Log.d("Migration", "Успешно обновлено игр: $updatedCount")

        } catch (e: Exception) {
            Log.e("Migration", "Ошибка миграции: ${e.message}", e)
        }
    }

    fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnGameDayClick -> onGameDayClick(action.item)
        }
    }

    private fun onGameDayClick(item: GameDayUiModel) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.tryEmit(true)

        }, catchBlock = { throwable ->
            throwable.printStackTrace()
            handleError(throwable)
        }, finallyBlock = {
            loadingState.tryEmit(false)
        })
    }

    private fun onMenuItemCLick(menuItem: MainMenuUiModel) {
        when (menuItem) {
            is MainMenuUiModel.Games -> {
                viewModelScope.launchJob(tryBlock = {
                    loadingState.tryEmit(true)
                }, catchBlock = { throwable ->
                    throwable.printStackTrace()
                    handleError(throwable)
                }, finallyBlock = {
                    loadingState.tryEmit(false)
                })
            }

            is MainMenuUiModel.Profile -> {
                navigateAction.tryEmit(MainScreenNavigateAction.Profile)
            }

            is MainMenuUiModel.Users -> {
                navigateAction.tryEmit(MainScreenNavigateAction.Users)
            }
        }
    }
}