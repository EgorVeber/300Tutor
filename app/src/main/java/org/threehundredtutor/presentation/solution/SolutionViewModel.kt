package org.threehundredtutor.presentation.solution

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.common.GetConfigUseCase
import org.threehundredtutor.domain.settings_app.GetSettingAppUseCase
import org.threehundredtutor.domain.solution.models.params_model.QuestionSolutionIdParamsModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.usecase.ChangeLikeQuestionUseCase
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.FinishSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.GetPointsUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionAnswersFlowUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.IsAllQuestionHaveAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationRemoveUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationSaveUseCase
import org.threehundredtutor.domain.solution.usecase.StartTestDirectoryUseCase
import org.threehundredtutor.domain.solution.usecase.StartTestUseCase
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.solution.mapper.toResultTestUiModel
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerInputUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerYourAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ResultButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.coroutines.launchJob
import org.threehundredtutor.ui_common.flow.SingleSharedFlow
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util.BadRequestException
import org.threehundredtutor.ui_core.SnackBarType
import java.util.Collections
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    private val solutionParamsDaggerModel: SolutionParamsDaggerModel,
    private val resourceProvider: ResourceProvider,
    private val solutionFactory: SolutionFactory,
    private val startTestUseCase: StartTestUseCase,
    private val startTestDirectoryUseCase: StartTestDirectoryUseCase,
    private val getSolutionUseCase: GetSolutionUseCase,
    private val getPointsUseCase: GetPointsUseCase,
    getConfigUseCase: GetConfigUseCase,
    private val checkAnswerUseCase: CheckAnswerUseCase,
    private val validationSaveUseCase: ResultQuestionsValidationSaveUseCase,
    private val validationRemoveUseCase: ResultQuestionsValidationRemoveUseCase,
    private val changeLikeQuestionUseCase: ChangeLikeQuestionUseCase,
    private val finishSolutionUseCase: FinishSolutionUseCase,
    private val getSolutionAnswersFlowUseCase: GetSolutionAnswersFlowUseCase,
    private val isAllQuestionHaveAnswerUseCase: IsAllQuestionHaveAnswerUseCase,
    private val getSettingAppUseCase: GetSettingAppUseCase,
) : BaseViewModel() {
    private val localConfig = getConfigUseCase()
    private val startTestMessage = resourceProvider.string(UiCoreStrings.start_test_message)

    private var resultTestUiModelCache: ResultTestUiModel? = null
    private var isFinished: Boolean = false
    private var rightAnswersCheckedMap: MutableMap<String, List<String>> = mutableMapOf()
    private var currentSolutionId: String = EMPTY_STRING

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val finishButtonState = MutableStateFlow(false)
    private val resultButtonState = MutableStateFlow(false)
    private val testInfoState = MutableStateFlow(EMPTY_STRING)
    private val answerCountState = MutableStateFlow(EMPTY_STRING)
    private val showResultDialogEventState = SingleSharedFlow<ResultTestUiModel>()
    private val errorState =
        MutableStateFlow(false)

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getShowResultDialogEventFlow() = showResultDialogEventState.asSharedFlow()
    fun getFinishButtonState() = finishButtonState.asStateFlow()
    fun getResultButtonState() = resultButtonState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getTestInfoStateFlow() = testInfoState.asSharedFlow()
    fun getErrorStateFlow() = errorState.asStateFlow()
    fun getTestResultState() = answerCountState.asStateFlow()

    init {
        val isGenerateTestByDirectory =
            solutionParamsDaggerModel.directoryTestId.isNotEmpty() && solutionParamsDaggerModel.workSpaceId.isNotEmpty()
        val isStartTest = solutionParamsDaggerModel.testId.isNotEmpty()
        val isGetSolution = solutionParamsDaggerModel.solutionId.isNotEmpty()

        getSolutionAnswersFlowUseCase().onEach { map ->
            // TODO не используем до введения локального сохранения ответов.
            answerCountState.update {
                resourceProvider.string(
                    UiCoreStrings.answer_solution_test_changed,
                    map.first,
                    map.second
                )
            }
        }.launchIn(viewModelScope)

        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val testSolutionModel = when {
                isGenerateTestByDirectory -> {
                    startTestDirectoryUseCase.invoke(
                        workSpaceId = solutionParamsDaggerModel.workSpaceId,
                        directoryId = solutionParamsDaggerModel.directoryTestId,
                        htmlPageTestType = solutionParamsDaggerModel.htmlPageTestType
                    )
                }

                isStartTest -> startTestUseCase.invoke(solutionParamsDaggerModel.testId)
                isGetSolution -> getSolutionUseCase.invoke(solutionParamsDaggerModel.solutionId)
                else -> throw BadRequestException()
            }

            currentSolutionId = testSolutionModel.solutionId
            isFinished = testSolutionModel.isFinished
            testInfoState.update { testSolutionModel.nameTest }

            uiItemsState.value = solutionFactory.createSolution(
                testSolutionGeneralModel = testSolutionModel,
                staticUrl = getSettingAppUseCase(false).publicImageUrlFormat
            )

            // Порядок не менять иначе все сломается
            when {
                testSolutionModel.isFinished -> {
                    resultButtonState.emit(true)
                    resultTestUiModelCache =
                        getPointsUseCase(currentSolutionId).toResultTestUiModel(resourceProvider)
                }

                isStartTest || isGenerateTestByDirectory -> {
                    uiEventState.emit(UiEvent.ShowSnack(startTestMessage, SnackBarType.SUCCESS))
                }

                isAllQuestionHaveAnswerUseCase() -> {
                    finishButtonState.emit(true)
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable) { errorState.update { true } }
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onFinishTestClicked() {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = finishSolutionUseCase.invoke(currentSolutionId)
            isFinished = result.isSucceeded
            if (result.isSucceeded) {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.INFO))
                var points = getPointsUseCase(solutionId = currentSolutionId)

                while (!points.hasPointsResult) {
                    delay(DELAY_RETRY_POINT_RESULT)
                    points = getPointsUseCase(solutionId = currentSolutionId)
                }

                val testSolutionModel = getSolutionUseCase.invoke(currentSolutionId)
                uiItemsState.value = solutionFactory.createSolution(
                    testSolutionGeneralModel = testSolutionModel,
                    staticUrl = getSettingAppUseCase(false).publicImageUrlFormat
                )
                resultButtonState.emit(true)
                finishButtonState.emit(false)
                val resultTestUiModel = points.toResultTestUiModel(resourceProvider)
                resultTestUiModelCache = resultTestUiModel
                showResultDialogEventState.tryEmit(resultTestUiModel)
            } else {
                uiEventState.emit(UiEvent.ShowMessage(result.message))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onAnswerWithErrorClicked(
        answerWithErrorsUiModel: AnswerWithErrorsUiModel,
        answer: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = answerWithErrorsUiModel.questionId,
                answerOrAnswers = answer
            )

            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                loadingState.update { false }
                return@launchJob
            }

            val newValue = AnswerWithErrorsResultUiItem(
                questionId = answerWithErrorsUiModel.questionId,
                answer = answer,
                rightAnswer = answerWithErrorsUiModel.rightAnswer,
                answerValidationResultType = answerModel.answerValidationResultType,
                pointString = getPointString(answerModel)
            )

            val currentList = uiItemsState.value.toMutableList()
            Collections.replaceAll(currentList, answerWithErrorsUiModel, newValue)
            uiItemsState.update { currentList }
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onRightAnswerClicked(
        rightAnswerUiModel: RightAnswerUiModel,
        inputAnswer: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = rightAnswerUiModel.questionId,
                answerOrAnswers = inputAnswer
            )

            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                loadingState.update { false }
                return@launchJob
            }

            val newValue = RightAnswerResultUiItem(
                answer = answerModel.answerOrAnswers,
                rightAnswer = rightAnswerUiModel.rightAnswers.joinToString(separator = "\n"),
                answerValidationResultType = answerModel.answerValidationResultType,
                pointsString = getPointString(answerModel),
                questionId = rightAnswerUiModel.questionId
            )

            val currentList = uiItemsState.value.toMutableList()
            Collections.replaceAll(currentList, rightAnswerUiModel, newValue)
            uiItemsState.update { currentList }
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onSelectRightAnswerCheckButtonClicked(
        selectRightAnswerCheckButtonUiItem: SelectRightAnswerCheckButtonUiItem
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val currentQuestionId = selectRightAnswerCheckButtonUiItem.questionId

            val answerJoin =
                rightAnswersCheckedMap[currentQuestionId].orEmpty().ifEmpty {
                    loadingState.update { false }
                    return@launchJob
                }.joinToString(separator = ANSWERS_SEPARATOR)

            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = currentQuestionId,
                answerOrAnswers = answerJoin
            )

            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                loadingState.update { false }
                return@launchJob
            }

            uiItemsState.update { uiItems ->
                uiItems.map { uiItem ->
                    if (uiItem is SelectRightAnswerUiModel && uiItem.questionId == currentQuestionId) {
                        uiItem.copy(isValidated = true)
                    } else {
                        uiItem
                    }
                }
            }

            val newItem = ResultButtonUiItem(
                questionId = currentQuestionId,
                answerModel.answerValidationResultType,
                pointString = getPointString(answerModel)
            )

            val currentList = uiItemsState.value.toMutableList()
            Collections.replaceAll(currentList, selectRightAnswerCheckButtonUiItem, newItem)
            uiItemsState.update { currentList }
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onDetailedAnswerClicked(
        detailedAnswerInputUiItem: DetailedAnswerInputUiItem,
        inputAnswer: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = detailedAnswerInputUiItem.questionId,
                answerOrAnswers = inputAnswer
            )
            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                loadingState.update { false }
                return@launchJob
            }

            val currentList = uiItemsState.value.toMutableList()
            val detailedAnswerInputIndex = currentList.indexOf(detailedAnswerInputUiItem)
            if (detailedAnswerInputIndex == -1 || currentList.size <= detailedAnswerInputIndex + 1) {
                throw IllegalArgumentException()
            }

            Collections.replaceAll(
                /* list = */ currentList,
                /* oldVal = */ detailedAnswerInputUiItem,
                /* newVal = */ DetailedAnswerYourAnswerUiItem(answerModel.answerOrAnswers)
            )

            val pointValidationItem = DetailedAnswerValidationUiItem(
                inputPoint = EMPTY_STRING,
                pointTotal = answerModel.pointsValidationModel.questionTotalPoints.toString(),
                questionId = answerModel.questionId,
                type = answerModel.answerValidationResultType, // Понаблюдать
                isValidated = answerModel.pointsValidationModel.isValidated,
                pointsString = getPointString(answerModel),
            )

            currentList.addAll(
                index = detailedAnswerInputIndex + 1,
                elements = detailedAnswerInputUiItem.explanationList + listOf(pointValidationItem)
            )

            uiItemsState.update { currentList }
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onDetailedAnswerValidationSaveClicked(
        answerValidationItemUiModel: DetailedAnswerValidationUiItem,
        inputPoint: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = validationSaveUseCase(
                SaveQuestionPointsValidationParamsModel(
                    answerPoints = inputPoint.toIntOrNull() ?: throw IllegalArgumentException(),
                    description = EMPTY_STRING,
                    questionSolutionIdParamsModel = QuestionSolutionIdParamsModel(
                        questionId = answerValidationItemUiModel.questionId,
                        solutionId = currentSolutionId
                    ),
                    questionTotalPoints = answerValidationItemUiModel.pointTotal.toInt()
                )
            )

            if (result.isSucceeded) {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.SUCCESS))
                val currentList = uiItemsState.value.toMutableList()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */answerValidationItemUiModel,
                    /* newVal = */ answerValidationItemUiModel.copy(
                        inputPoint = result.answerModel.pointsValidationModel.answerPoints.toString(),
                        type = result.answerModel.answerValidationResultType,
                        isValidated = result.answerModel.pointsValidationModel.isValidated,
                        pointsString = getPointString(result.answerModel)
                    )
                )
                uiItemsState.update { currentList }
                if (isFinished) {
                    resultTestUiModelCache =
                        getPointsUseCase(currentSolutionId).toResultTestUiModel(resourceProvider)
                }
            } else {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.ERROR))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onDeleteValidationClicked(detailedAnswerValidationUiItem: DetailedAnswerValidationUiItem) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = validationRemoveUseCase.invoke(
                solutionId = currentSolutionId,
                questionId = detailedAnswerValidationUiItem.questionId
            )

            if (result.isSucceeded) {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.SUCCESS))
                val currentList = uiItemsState.value.toMutableList()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */detailedAnswerValidationUiItem,
                    /* newVal = */ detailedAnswerValidationUiItem.copy(
                        inputPoint = result.answerModel.pointsValidationModel.answerPoints.toString(),
                        type = result.answerModel.answerValidationResultType,
                        isValidated = result.answerModel.pointsValidationModel.isValidated,
                    )
                )
                uiItemsState.update { currentList }

                if (isFinished) {
                    resultTestUiModelCache =
                        getPointsUseCase(currentSolutionId).toResultTestUiModel(resourceProvider)
                }

            } else {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.ERROR))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onDetailedAnswerTextChanged(questionId: String, inputAnswer: String) {
        uiItemsState.update { uiItems ->
            uiItems.map { uiItem ->
                if (uiItem is DetailedAnswerInputUiItem && uiItem.questionId == questionId) {
                    uiItem.copy(inputAnswer = inputAnswer)
                } else {
                    uiItem
                }
            }
        }
    }

    fun onRightAnswerTextChanged(questionId: String, inputAnswer: String) {
        uiItemsState.update { uiItems ->
            uiItems.map { uiItem ->
                if (uiItem is RightAnswerUiModel && uiItem.questionId == questionId) {
                    uiItem.copy(inputAnswer = inputAnswer)
                } else {
                    uiItem
                }
            }
        }
    }

    fun onAnswerWithErrorsTextChanged(questionId: String, inputAnswer: String) {
        uiItemsState.update { uiItems ->
            uiItems.map { uiItem ->
                if (uiItem is AnswerWithErrorsUiModel && uiItem.questionId == questionId) {
                    uiItem.copy(inputAnswer = inputAnswer)
                } else {
                    uiItem
                }
            }
        }
    }

    fun onSelectRightAnswerCheckedChange(
        questionId: String,
        answerText: String,
        checked: Boolean
    ) {
        val answersList = rightAnswersCheckedMap[questionId] ?: emptyList()
        if (checked) {
            rightAnswersCheckedMap[questionId] = answersList + listOf(answerText)
        } else {
            rightAnswersCheckedMap[questionId] = answersList - listOf(answerText).toSet()
        }

        uiItemsState.update { uiItems ->
            uiItems.map { uiItem ->
                if (uiItem is SelectRightAnswerUiModel && uiItem.questionId == questionId && uiItem.answer == answerText) {
                    uiItem.copy(checked = checked)
                } else {
                    uiItem
                }
            }
        }
    }

    fun onYoutubeClicked(link: String) {
        uiEventState.tryEmit(UiEvent.OpenYoutube(link))
    }

    fun onImageClicked(imageId: String) {
        if (imageId.isNotEmpty()) {
            viewModelScope.launchJob(tryBlock = {
                uiEventState.tryEmit(
                    UiEvent.NavigatePhotoDetailed(
                        imagePath =
                        SolutionFactory.replaceUrl(
                            url = getSettingAppUseCase(false).publicImageUrlFormat,
                            fileId = imageId,
                            type = SolutionFactory.IMAGE_ORIGINAL_TYPE
                        )
                    )
                )
            }, catchBlock = { throwable ->
                handleError(throwable)
            })
        }
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
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.SUCCESS))
            } else {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.ERROR))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onResultTestClicked() {
        if (!isFinished) return
        resultTestUiModelCache?.let { resultTestUiModel ->
            showResultDialogEventState.tryEmit(resultTestUiModel)
        }
    }

    // TODO не использует до введения локалькного сохранения ответов. После будем смотреть если есть локально введенные не пролвернные ответы то показываем диалог с предупреждением что локаольноые ответы не сохроняться.
    fun onBackClicked() {
        if (isFinished || errorState.value) {
            uiEventState.tryEmit(UiEvent.NavigateBack)
        } else {
            uiEventState.tryEmit(UiEvent.ShowFinishDialog)
        }
    }

    private fun getPointString(answerModel: AnswerModel): String {
        if (!answerModel.pointsValidationModel.isValidated) return EMPTY_STRING
        return resourceProvider.string(
            UiCoreStrings.points_question,
            answerModel.pointsValidationModel.answerPoints,
            answerModel.pointsValidationModel.questionTotalPoints
        )
    }

    private var visibleFinishTestFlag = false

    fun onLastItemVisible() {
        if (visibleFinishTestFlag) return
        visibleFinishTestFlag = true
        finishButtonState.tryEmit(true)
    }

    sealed interface UiEvent {
        data class ShowSnack(val message: String, val snackBarType: SnackBarType) : UiEvent

        @JvmInline
        value class NavigatePhotoDetailed(val imagePath: String) : UiEvent

        @JvmInline
        value class ShowMessage(val message: String) : UiEvent

        @JvmInline
        value class OpenYoutube(val link: String) : UiEvent

        object ShowFinishDialog : UiEvent
        object NavigateBack : UiEvent
    }

    companion object {
        const val ANSWERS_SEPARATOR = ";"
        const val DELAY_RETRY_POINT_RESULT = 2000L
    }
}