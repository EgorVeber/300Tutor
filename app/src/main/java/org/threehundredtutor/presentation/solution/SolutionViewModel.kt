package org.threehundredtutor.presentation.solution

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.common.utils.SnackBarType
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.QuestionSolutionIdParamsModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.usecase.ChangeLikeQuestionUseCase
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.FinishSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.GetPointsUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.IsAllQuestionHaveAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationRemoveUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationSaveUseCase
import org.threehundredtutor.domain.solution.usecase.StartTestUseCase
import org.threehundredtutor.presentation.solution.mapper.toResultTestUiModel
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ResultButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.AnswerSelectRightUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import java.util.Collections
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    private val solutionFactory: SolutionFactory,
    private val getSolutionUseCase: GetSolutionUseCase,
    private val startTestUseCase: StartTestUseCase,
    private val checkAnswerUseCase: CheckAnswerUseCase,
    private val validationSaveUseCase: ResultQuestionsValidationSaveUseCase,
    private val validationRemoveUseCase: ResultQuestionsValidationRemoveUseCase,
    private val finishSolutionUseCase: FinishSolutionUseCase,
    private val isAllQuestionHaveAnswerUseCase: IsAllQuestionHaveAnswerUseCase,
    private val getPointsUseCase: GetPointsUseCase,
    private val resourceProvider: ResourceProvider,
    private val changeLikeQuestionUseCase: ChangeLikeQuestionUseCase,
) : BaseViewModel() {

    private var currentSolutionId: String = EMPTY_STRING
    private var resultTestUiModelCache: ResultTestUiModel? = null

    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()
    private var isFinished: Boolean = false

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val finishButtonState = MutableStateFlow(false)
    private val resultButtonState = MutableStateFlow(false)
    private val testInfoState = MutableStateFlow(EMPTY_STRING)
    private val resultTestState = SingleSharedFlow<ResultTestUiModel>()
    private val errorState = MutableStateFlow(false)

    private var firstViewAttach = true

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getResultTestStateFlow() = resultTestState.asSharedFlow()
    fun getFinishButtonState() = finishButtonState.asStateFlow()
    fun getResultButtonState() = resultButtonState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getTestInfoStateFlow() = testInfoState.asSharedFlow()
    fun getErrorStateFlow() = errorState.asStateFlow()

    fun onViewInitiated(testId: String, solutionId: String) {
        if (!firstViewAttach) return
        loadTest(testId = testId, solutionId = solutionId, needUpdateResultTest = true)
    }

    private fun loadTest(testId: String, solutionId: String, needUpdateResultTest: Boolean) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            firstViewAttach = false
            val testSolutionModel = getSolution(testId, solutionId)

            currentSolutionId = testSolutionModel.solutionId

            isFinished = testSolutionModel.isFinished
            testInfoState.update { testSolutionModel.nameTest }

            val uiItemList = solutionFactory.createSolution(testSolutionModel)
            uiItemsState.update { uiItemList }

            if (testId.isNotEmpty()) {
                uiEventState.emit(
                    UiEvent.ShowSnack(
                        resourceProvider.string(R.string.start_test_message),
                        SnackBarType.SUCCESS
                    )
                )
            }

            if (!testSolutionModel.isFinished && isAllQuestionHaveAnswerUseCase()) {
                finishButtonState.emit(true)
            }

            if (testSolutionModel.isFinished) {
                resultButtonState.emit(true)
                if (needUpdateResultTest) {
                    resultTestUiModelCache =
                        getPointsUseCase(solutionId).toResultTestUiModel(resourceProvider)
                }
            }
        }, catchBlock = { throwable ->
            handleError(throwable) {
                errorState.update { true }
            }
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onAnswerWithErrorClicked(
        answerWithErrorsUiModel: AnswerWithErrorsUiModel, answer: String
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
                return@launchJob
            }

            val currentList = uiItemsState.value.toMutableList()

            val pointString = getPointString(answerModel)
            val newValue = AnswerWithErrorsResultUiItem(
                questionId = answerWithErrorsUiModel.questionId,
                answer = answer,
                rightAnswer = answerWithErrorsUiModel.rightAnswer,
                answerValidationResultType = answerModel.answerValidationResultType,
                pointString = pointString
            )

            Collections.replaceAll(currentList, answerWithErrorsUiModel, newValue)
            uiItemsState.update { currentList }


            isAllQuestionHaveAnswer()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onRightAnswerClicked(rightAnswerUiModel: RightAnswerUiModel, answer: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = rightAnswerUiModel.questionId,
                answerOrAnswers = answer
            )

            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                return@launchJob
            }

            val pointsString = getPointString(answerModel)
            val currentList = uiItemsState.value.toMutableList()
            val newValue = RightAnswerResultUiItem(
                answer = answer,
                rightAnswer = rightAnswerUiModel.rightAnswers.joinToString(separator = "\n"),
                answerValidationResultType = answerModel.answerValidationResultType,
                pointsString = pointsString,
                questionId = rightAnswerUiModel.questionId
            )

            Collections.replaceAll(currentList, rightAnswerUiModel, newValue)
            uiItemsState.update { currentList }
            isAllQuestionHaveAnswer()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onSelectRightAnswerCheckButtonClicked(questionId: String) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val answerJoin = questionMap.getOrPut(key = questionId, defaultValue = { emptyList() })
                .joinToString(separator = ANSWERS_SEPARATOR)
            if (answerJoin.isEmpty()) return@launchJob

            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = questionId,
                answerOrAnswers = answerJoin
            )

            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                return@launchJob
            }

            val currentList = uiItemsState.value.toMutableList().map { solutionUiItem ->
                if (solutionUiItem is AnswerSelectRightUiModel && solutionUiItem.questionId == questionId) {
                    solutionUiItem.copy(enabled = false)
                } else {
                    solutionUiItem
                }
            }

            val oldItem = currentList.find { solutionUiItem ->
                solutionUiItem is SelectRightAnswerCheckButtonUiItem && solutionUiItem.questionId == questionId
            }

            oldItem ?: throw IllegalArgumentException()

            val pointString = getPointString(answerModel)

            val newItem = ResultButtonUiItem(
                questionId = questionId,
                answerModel.answerValidationResultType,
                pointString = pointString
            )

            Collections.replaceAll(currentList, oldItem, newItem)

            uiItemsState.update { currentList }
            isAllQuestionHaveAnswer()
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onCheckedChangeSelectRightAnswer(questionId: String, answerText: String, checked: Boolean) {
        val answersList = questionMap.getOrPut(key = questionId, defaultValue = { emptyList() })
        if (checked) {
            questionMap[questionId] = answersList + listOf(answerText)
        } else {
            questionMap[questionId] = answersList - listOf(answerText).toSet()
        }

        val currentList = uiItemsState.value.toMutableList().map { solutionUiItem ->
            if (solutionUiItem is AnswerSelectRightUiModel && solutionUiItem.questionId == questionId && solutionUiItem.answer == answerText) {
                solutionUiItem.copy(checked = checked)
            } else {
                solutionUiItem
            }
        }

        uiItemsState.update { currentList }
    }

    fun onDetailedAnswerClicked(detailedAnswerUiItem: DetailedAnswerUiItem, answer: String) {
        viewModelScope.launchJob(tryBlock = {
            val (isSucceeded, message, answerModel) = checkAnswerUseCase(
                solutionId = currentSolutionId,
                questionId = detailedAnswerUiItem.questionId,
                answerOrAnswers = answer
            )
            if (!isSucceeded) {
                uiEventState.emit(UiEvent.ShowMessage(message))
                return@launchJob
            }

            val currentList = uiItemsState.value.toMutableList()
            val index = currentList.indexOf(detailedAnswerUiItem)
            if (index == -1) throw IllegalArgumentException()

            Collections.replaceAll(
                /* list = */ currentList,
                /* oldVal = */ detailedAnswerUiItem,
                /* newVal = */ DetailedAnswerResultUiItem(answer)
            )

            val pointString = getPointString(answerModel)

            if (currentList.size >= index + 1) {
                currentList.addAll(
                    index + 1, detailedAnswerUiItem.explanationList + listOf(
                        DetailedAnswerValidationUiItem(
                            inputPoint = EMPTY_STRING,
                            pointTotal = answerModel.pointsValidationModel.questionTotalPoints.toString(),
                            questionId = detailedAnswerUiItem.questionId,
                            type = AnswerValidationResultType.UNKNOWN,
                            isValidated = answerModel.pointsValidationModel.isValidated,
                            pointsString = pointString,
                        )
                    )
                )

                uiItemsState.update { currentList }
            } else throw IllegalArgumentException()

            isAllQuestionHaveAnswer()
        }, catchBlock = { throwable ->
            handleError(throwable)
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
                val newItem = currentList.filterIsInstance<DetailedAnswerValidationUiItem>().find {
                    it.questionId == answerValidationItemUiModel.questionId
                }
                newItem ?: throw IllegalArgumentException()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */answerValidationItemUiModel,
                    /* newVal = */ answerValidationItemUiModel.copy(
                        inputPoint = inputPoint,
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

    fun onDeleteValidationClicked(answerValidationItemUiModel: DetailedAnswerValidationUiItem) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            val result = validationRemoveUseCase.invoke(
                solutionId = currentSolutionId,
                questionId = answerValidationItemUiModel.questionId
            )
            if (result.isSucceeded) {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.SUCCESS))
                val currentList = uiItemsState.value.toMutableList()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */answerValidationItemUiModel,
                    /* newVal = */ answerValidationItemUiModel.copy(
                        inputPoint = EMPTY_STRING,
                        type = AnswerValidationResultType.UNKNOWN,
                        isValidated = false,
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

    fun onYoutubeClicked(link: String) {
        uiEventState.tryEmit(UiEvent.OpenYoutube(link))
    }

    fun onImageClicked(imageId: String) {
        if (imageId.isNotEmpty()) uiEventState.tryEmit(UiEvent.NavigatePhotoDetailed(imageId))
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
                    delay(DELAY_POINT_RESULT)
                    points = getPointsUseCase(solutionId = currentSolutionId)
                }
                loadTest(
                    testId = EMPTY_STRING,
                    solutionId = currentSolutionId,
                    needUpdateResultTest = false
                )

                finishButtonState.emit(false)
                resultButtonState.emit(true)
                updateResultTest(points.toResultTestUiModel(resourceProvider))
            } else {
                uiEventState.emit(UiEvent.ShowMessage(result.message))
            }
        }, catchBlock = { throwable ->
            handleError(throwable)
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    fun onBackClicked(errorVisible: Boolean) {
        if (isFinished || errorVisible) {
            uiEventState.tryEmit(UiEvent.NavigateBack)
        } else {
            uiEventState.tryEmit(UiEvent.ShowFinishDialog)
        }
    }


    fun onDetailedAnswerTextChanged(item: DetailedAnswerUiItem, text: String) {
        val currentList = uiItemsState.value.toMutableList()
        Collections.replaceAll(
            /* list = */ currentList,
            /* oldVal = */item,
            /* newVal = */ item.copy(
                inputAnswer = text
            )
        )
        uiItemsState.update { currentList }
    }

    fun onQuestionLikeClicked(headerUiItem: HeaderUiItem) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            //  TODO Придумать че делать № LikeNotValidation
            val result = changeLikeQuestionUseCase.invoke(
                questionId = headerUiItem.questionId,
                hasLike = !headerUiItem.isQuestionLikedByStudent
            )

            if (result.isSucceeded) {
                uiEventState.emit(UiEvent.ShowSnack(result.message, SnackBarType.SUCCESS))
                val currentList = uiItemsState.value.toMutableList()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */headerUiItem,
                    /* newVal = */ headerUiItem.copy(
                        isQuestionLikedByStudent = !headerUiItem.isQuestionLikedByStudent
                    )
                )
                uiItemsState.update { currentList }
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
        if (isFinished) {
            runCatching {
                updateResultTest(resultTestUiModelCache ?: throw IllegalArgumentException())
            }.onFailure { throwable ->
                handleError(throwable)
            }
        }
    }

    private fun getPointString(answerModel: AnswerModel): String =
        if (answerModel.pointsValidationModel.isValidated) {
            resourceProvider.string(
                R.string.points_question,
                answerModel.pointsValidationModel.answerPoints,
                answerModel.pointsValidationModel.questionTotalPoints
            )
        } else EMPTY_STRING

    private suspend fun isAllQuestionHaveAnswer() {
        if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
    }

    private fun updateResultTest(resultTestUiModel: ResultTestUiModel) {
        resultTestUiModelCache = resultTestUiModel
        resultTestState.tryEmit(resultTestUiModel)
    }

    private suspend fun getSolution(
        testId: String,
        solutionId: String
    ): TestSolutionGeneralModel {
        val testSolutionModel = if (testId.isNotEmpty()) {
            startTestUseCase.invoke(testId)
        } else {
            getSolutionUseCase.invoke(solutionId)
        }
        return testSolutionModel
    }

    sealed interface UiEvent {
        data class ShowMessage(val message: String) : UiEvent
        data class OpenYoutube(val link: String) : UiEvent
        data class NavigatePhotoDetailed(val imageId: String) : UiEvent
        data class ShowSnack(val message: String, val snackBarType: SnackBarType) : UiEvent
        object ShowFinishDialog : UiEvent
        object NavigateBack : UiEvent

        // TODO стейты на будущие
        data class ScrollToQuestion(val position: Int) : UiEvent
        object ErrorSolution : UiEvent
    }

    companion object {
        const val ANSWERS_SEPARATOR = ";"
        const val DELAY_POINT_RESULT = 2000L
    }
}