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
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.QuestionSolutionIdParamsModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.points.SolutionPointsQuestionModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.FinishSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.GetPointsUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.IsAllQuestionHaveAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationSaveUseCase
import org.threehundredtutor.domain.solution.usecase.StartTestUseCase
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
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
    private val finishSolutionUseCase: FinishSolutionUseCase,
    private val isAllQuestionHaveAnswerUseCase: IsAllQuestionHaveAnswerUseCase,
    private val getPointsUseCase: GetPointsUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private var currentSolutionId: String = EMPTY_STRING

    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()
    private var isFinished: Boolean = false

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val finishButtonState = MutableStateFlow(false)
    private val testInfoState = MutableStateFlow<TestSolutionGeneralModel?>(null)
    private val resultTestState = MutableStateFlow<ResultTestUiModel?>(null)
    private val errorState = MutableStateFlow(false)

    private var firstViewAttach = true

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getResultTestStateStateFlow() = resultTestState.asStateFlow()
    fun getFinishButtonState() = finishButtonState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getTestInfoStateFlow() = testInfoState.asSharedFlow()
    fun getErrorStateFlow() = errorState.asStateFlow()

    fun onViewInitiated(subjectId: String, solutionId: String) {
        viewModelScope.launchJob(tryBlock = {
            if (!firstViewAttach) return@launchJob
            loadingState.update { true }
            firstViewAttach = false
            val testSolutionModel = getSolution(subjectId, solutionId)

            currentSolutionId = testSolutionModel.solutionId

            isFinished = testSolutionModel.isFinished
            testInfoState.update { testSolutionModel }

            val uiItemList = solutionFactory.createSolution(testSolutionModel)
            uiItemsState.update { uiItemList }

            if (!testSolutionModel.isFinished && isAllQuestionHaveAnswerUseCase()) {
                finishButtonState.emit(true)
            }

            if (isFinished) {
                val pointInfo = getPointsUseCase(solutionId = currentSolutionId)
                val questionCountNeedCheck =
                    pointInfo.solutionPointsQuestionModel.filter { solutionPointsQuestionModel: SolutionPointsQuestionModel ->
                        solutionPointsQuestionModel.resultType == AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF
                    }.size

                resultTestState.emit(
                    ResultTestUiModel(
                        questionRight = pointInfo.hasRightAnswerQuestionsCount.toString(),
                        questionCount = pointInfo.questionsCount.toString(),
                        answerPointsAll = pointInfo.studentTotalPoints.toString(),
                        questionTotalPointsAll = pointInfo.maxTotalPoints.toString(),
                        questionCountNeedCheck = if (questionCountNeedCheck > 0) questionCountNeedCheck.toString() else EMPTY_STRING
                    )
                )
            }
        }, catchBlock = { throwable ->
            handleError(throwable) {
                errorState.update { true }
            }
        }, finallyBlock = {
            loadingState.update { false }
        })
    }

    private suspend fun getSolution(
        subjectId: String,
        solutionId: String
    ): TestSolutionGeneralModel {
        val testSolutionModel = if (subjectId.isNotEmpty()) {
            startTestUseCase.invoke(subjectId)
        } else {
            getSolutionUseCase.invoke(solutionId)
        }
        return testSolutionModel
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


            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
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

            val pointsString = with(answerModel) {
                if (pointsValidationModel.isValidated) {
                    resourceProvider.string(
                        R.string.points_question,
                        answerModel.pointsValidationModel.answerPoints,
                        answerModel.pointsValidationModel.questionTotalPoints
                    )
                } else EMPTY_STRING
            }

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
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
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
            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
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
                currentList,
                detailedAnswerUiItem,
                DetailedAnswerResultUiItem(answer)
            )

            val pointString = getPointString(answerModel)

            if (currentList.size >= index + 1) {
                // TODO РАЗОБРАТЬ откуда сдесь  pointString
                currentList.addAll(
                    index + 1, detailedAnswerUiItem.explanationList + listOf(
                        ResultButtonUiItem(
                            questionId = detailedAnswerUiItem.questionId,
                            answerValidationResultType = AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF,
                            pointString = pointString
                        ),
                        DetailedAnswerValidationUiItem(
                            inputPoint = EMPTY_STRING,
                            pointTotal = answerModel.pointsValidationModel.questionTotalPoints.toString(),
                            questionId = detailedAnswerUiItem.questionId,
                            type = AnswerValidationResultType.UNKNOWN,
                            isValidated = answerModel.pointsValidationModel.isValidated,
                            pointsString = pointString
                        )
                    )
                )
                uiItemsState.update { currentList }
            } else throw IllegalArgumentException()

            if (isAllQuestionHaveAnswerUseCase()) finishButtonState.emit(true)
        }, catchBlock = { throwable ->
            handleError(throwable)
        })
    }

    fun onDetailedAnswerValidationClicked(
        answerValidationItemUiModel: DetailedAnswerValidationUiItem,
        inputPoint: String
    ) {
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }

            val result = validationSaveUseCase(
                SaveQuestionPointsValidationParamsModel(
                    answerPoints = inputPoint.toInt(),
                    description = EMPTY_STRING,
                    questionSolutionIdParamsModel = QuestionSolutionIdParamsModel(
                        questionId = answerValidationItemUiModel.questionId,
                        solutionId = currentSolutionId
                    ),
                    questionTotalPoints = answerValidationItemUiModel.pointTotal.toInt()
                )
            )

            uiEventState.emit(UiEvent.ShowMessage(result.message))

            if (result.isSucceeded) {
                val currentList = uiItemsState.value.toMutableList()
                val newItem = currentList.filterIsInstance<DetailedAnswerValidationUiItem>().find {
                    it.questionId == answerValidationItemUiModel.questionId
                }
                newItem ?: throw IllegalArgumentException()
                Collections.replaceAll(
                    /* list = */ currentList,
                    /* oldVal = */answerValidationItemUiModel,
                    /* newVal = */ newItem.copy(inputPoint = inputPoint)
                )
                uiItemsState.update { currentList }
            }

            if (isFinished) {
                firstViewAttach = true
                onViewInitiated(subjectId = EMPTY_STRING, solutionId = currentSolutionId)
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
            uiEventState.emit(UiEvent.ShowSnack(result.message))
            isFinished = result.isSucceeded

            finishButtonState.emit(false)

            if (result.isSucceeded) {
                var points = getPointsUseCase(solutionId = currentSolutionId)
                while (!points.hasPointsResult) {
                    delay(DELAY_POINT_RESULT)
                    points = getPointsUseCase(solutionId = currentSolutionId)
                }
                firstViewAttach = true
                onViewInitiated(subjectId = EMPTY_STRING, solutionId = currentSolutionId)
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

    private fun getPointString(answerModel: AnswerModel): String =
        if (answerModel.pointsValidationModel.isValidated) {
            resourceProvider.string(
                R.string.points_question,
                answerModel.pointsValidationModel.answerPoints,
                answerModel.pointsValidationModel.questionTotalPoints
            )
        } else EMPTY_STRING

    sealed interface UiEvent {
        data class ShowMessage(val message: String) : UiEvent
        data class OpenYoutube(val link: String) : UiEvent
        data class NavigatePhotoDetailed(val imageId: String) : UiEvent
        data class ShowSnack(val message: String) : UiEvent
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