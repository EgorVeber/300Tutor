package org.threehundredtutor.presentation.solution

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.SingleSharedFlow
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.params_model.QuestionSolutionIdParamsModel
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.usecase.CheckAnswerUseCase
import org.threehundredtutor.domain.solution.usecase.GetSolutionUseCase
import org.threehundredtutor.domain.solution.usecase.ResultQuestionsValidationSaveUseCase
import org.threehundredtutor.domain.solution.usecase.StartTestUseCase
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory
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
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)
    private val uiItemsState = MutableStateFlow<List<SolutionUiItem>>(listOf())
    private val uiEventState = SingleSharedFlow<UiEvent>()
    private val testInfoState = MutableStateFlow<TestSolutionGeneralModel?>(null)

    private var currentSolutionId: String = EMPTY_STRING
    private var currentSubjectTestId: String = EMPTY_STRING

    fun getUiItemStateFlow() = uiItemsState.asStateFlow()
    fun getLoadingStateFlow() = loadingState.asStateFlow()
    fun getUiEventStateFlow() = uiEventState.asSharedFlow()
    fun getTestInfoStateFlow() = testInfoState.asSharedFlow()

    private var questionMap: MutableMap<String, List<String>> = mutableMapOf()

    fun onViewInitiated(subjectTestId: String, isSubject: Boolean) {
        if (currentSubjectTestId == subjectTestId) return
        viewModelScope.launchJob(tryBlock = {
            loadingState.update { true }
            //TODO TutorAndroid-38
            currentSubjectTestId = subjectTestId

            val testSolutionModel = if (isSubject) {
                startTestUseCase.invoke(currentSubjectTestId)
            } else {
                getSolutionUseCase.invoke(subjectTestId)
            }



            currentSolutionId = testSolutionModel.solutionId
            testInfoState.update { testSolutionModel }
            val uiItemList = solutionFactory.createSolution(testSolutionModel)
            uiItemsState.update { uiItemList }

        }, catchBlock = { throwable ->
            handleError(throwable)
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
            val newValue = AnswerWithErrorsResultUiItem(
                answer = answer,
                rightAnswer = answerWithErrorsUiModel.rightAnswer,
                answerValidationResultType = answerModel.answerValidationResultType
            )

            Collections.replaceAll(currentList, answerWithErrorsUiModel, newValue)
            uiItemsState.update { currentList }
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

            val currentList = uiItemsState.value.toMutableList()
            val newValue = RightAnswerResultUiItem(
                answer = answer,
                rightAnswer = rightAnswerUiModel.rightAnswers.joinToString(separator = "\n"),
                answerValidationResultType = answerModel.answerValidationResultType
            )

            Collections.replaceAll(currentList, rightAnswerUiModel, newValue)
            uiItemsState.update { currentList }
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

            val newItem = ResultButtonUiItem(answerModel.answerValidationResultType)
            Collections.replaceAll(currentList, oldItem, newItem)

            uiItemsState.update { currentList }
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

            if (currentList.size >= index + 1) {
                currentList.addAll(
                    index + 1, detailedAnswerUiItem.explanationList + listOf(
                        ResultButtonUiItem(AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF),
                        DetailedAnswerValidationUiItem(
                            inputPoint = EMPTY_STRING,
                            pointTotal = answerModel.pointsValidationModel.questionTotalPoints.toString(),
                            questionId = detailedAnswerUiItem.questionId,
                            type = AnswerValidationResultType.UNKNOWN,
                            isValidated = answerModel.pointsValidationModel.isValidated
                        )
                    )
                )
                uiItemsState.update { currentList }
            } else throw IllegalArgumentException()
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

    sealed interface UiEvent {
        data class ShowMessage(val message: String) : UiEvent
        data class OpenYoutube(val link: String) : UiEvent
        data class NavigatePhotoDetailed(val imageId: String) : UiEvent

        // TODO стейты на будущие
        data class ShowSnack(val message: String, val typeColorSnackBar: String) : UiEvent
        data class ScrollToQuestion(val position: Int) : UiEvent
        object ErrorSolution : UiEvent
    }

    companion object {
        const val ANSWERS_SEPARATOR = ";"
    }
}