package org.threehundredtutor.presentation.favorites

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.account.usecase.GetAccountUseCase
import org.threehundredtutor.domain.favorites.GetFavoritesQuestionUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.domain.solution.usecase.ChangeLikeQuestionUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_core.SnackBarType
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoritesQuestionUseCase: GetFavoritesQuestionUseCase,
    private val resourceProvider: ResourceProvider,
    private val getSettingAppUseCase: GetSettingAppUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val solutionFactory: SolutionFactory,
    private val changeLikeQuestionUseCase: ChangeLikeQuestionUseCase,
) : BaseViewModel() {

    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val loadingState = MutableStateFlow(false)
    private val uiEventState = SingleSharedFlow<UiEvent>()

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingState() = loadingState.asStateFlow()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val studentId = getAccountUseCase.invoke(false).userId
            val questions = getFavoritesQuestionUseCase(studentId)

            val uiItems = solutionFactory.createFavoritesQuestion(
                questionList = questions,
                staticUrl = getSettingAppUseCase(false).publicImageUrlFormat
            ).ifEmpty {
                listOf(EmptyUiItem(resourceProvider.string(UiCoreStrings.favorites_empty)))
            }
            uiItemsState.update { uiItems }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onQuestionLikeClicked(headerUiItem: HeaderUiItem) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val result = changeLikeQuestionUseCase.invoke(
                questionId = headerUiItem.questionId,
                hasLike = !headerUiItem.isQuestionLikedByStudent
            )

            if (result.isSucceeded) {
                uiItemsState.update { uiItems ->
                    uiItems.map { uiItem ->
                        if (headerUiItem == uiItem) {
                            headerUiItem.copy(
                                isQuestionLikedByStudent = !headerUiItem.isQuestionLikedByStudent
                            )
                        } else {
                            uiItem
                        }
                    }
                }
                uiEventState.emit(
                    UiEvent.ShowSnack(
                        result.message,
                        SnackBarType.SUCCESS
                    )
                )
            } else {
                uiEventState.emit(
                    UiEvent.ShowSnack(
                        result.message,
                        SnackBarType.ERROR
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onRefresh() {
        loadData()
    }

    fun onImageClicked(imageId: String) {
        if (imageId.isNotEmpty()) {
            viewModelScope.launchJob(tryBlock = {
                uiEventState.tryEmit(
                    UiEvent.NavigatePhotoDetailed(
                        imagePath = SolutionFactory.replaceUrl(
                            getSettingAppUseCase(force = false).publicImageUrlFormat,
                            imageId,
                            SolutionFactory.IMAGE_ORIGINAL_TYPE
                        )
                    )
                )
            }, catchBlock = { throwable ->
                handleError(throwable)
            })
        }
    }

    sealed interface UiEvent {
        @JvmInline
        value class NavigatePhotoDetailed(val imagePath: String) : UiEvent
        data class ShowSnack(val message: String, val snackBarType: SnackBarType) : UiEvent
    }
}