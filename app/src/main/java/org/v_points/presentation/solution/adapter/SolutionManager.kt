package org.v_points.presentation.solution.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getAnswerSelectRightUiModelAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getAnswerWithErrorsResultUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getAnswerWithErrorsUiModelAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerResultUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerValidationUiItemAdapted
import org.v_points.presentation.solution.adapter.SolutionAdapters.getDividerUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getFavoritesAnswerWithErrorsResultUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getFooterUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getHeaderUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getImageUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getResultButtonUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getRightAnswerResultUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getRightAnswerUiModelAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerCheckButtonUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerTitleUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSeparatorUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSuccessAnswerTitleUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSupSubUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getTextUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getYoutubeUiItemAdapter
import org.v_points.presentation.solution.ui_models.SolutionUiItem
import org.v_points.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.v_points.presentation.solution.ui_models.detailed_answer.DetailedAnswerInputUiItem
import org.v_points.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.v_points.presentation.solution.ui_models.item_common.HeaderUiItem
import org.v_points.presentation.solution.ui_models.right_answer.RightAnswerUiModel
import org.v_points.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import org.v_points.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel

class SolutionManager(
    imageClickListener: (String) -> Unit,
    youtubeClickListener: (String) -> Unit,
    selectRightAnswerClickListener: (String, String, Boolean) -> Unit,
    selectRightAnswerCheckButtonClickListener: (SelectRightAnswerCheckButtonUiItem) -> Unit,
    rightAnswerTextChangedListener: (String, String) -> Unit,
    rightAnswerClickListener: (RightAnswerUiModel, String) -> Unit,
    answerWithErrorsTextChangedListener: (String, String) -> Unit,
    answerWithErrorsClickListener: (AnswerWithErrorsUiModel, String) -> Unit,
    detailedAnswerClickListener: (DetailedAnswerInputUiItem, String) -> Unit,
    detailedAnswerTextChangedListener: (String, String) -> Unit,
    detailedAnswerValidationClickListener: (DetailedAnswerValidationUiItem, String) -> Unit,
    deleteValidationClickListener: (DetailedAnswerValidationUiItem) -> Unit,
    questionLikeClickListener: (HeaderUiItem) -> Unit
) : AsyncListDifferDelegationAdapter<SolutionUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getHeaderUiItemAdapter(questionLikeClickListener))
            .addDelegate(getFooterUiItemAdapter())
            .addDelegate(getTextUiItemAdapter())
            .addDelegate(getImageUiItemAdapter(imageClickListener))
            .addDelegate(getYoutubeUiItemAdapter(youtubeClickListener))
            .addDelegate(getSupSubUiItemAdapter())
            .addDelegate(getDividerUiItemAdapter())
            .addDelegate(getSeparatorUiItemAdapter())
            .addDelegate(getResultButtonUiItemAdapter())
            .addDelegate(getSelectRightAnswerTitleUiItemAdapter())
            .addDelegate(getAnswerSelectRightUiModelAdapter(selectRightAnswerClickListener))
            .addDelegate(
                getSelectRightAnswerCheckButtonUiItemAdapter(
                    selectRightAnswerCheckButtonClickListener
                )
            )
            .addDelegate(
                getRightAnswerUiModelAdapter(
                    rightAnswerClickListener,
                    rightAnswerTextChangedListener
                )
            )
            .addDelegate(getRightAnswerResultUiItemAdapter())
            .addDelegate(
                getAnswerWithErrorsUiModelAdapter(
                    answerWithErrorsTextChangedListener,
                    answerWithErrorsClickListener
                )
            )
            .addDelegate(getAnswerWithErrorsResultUiItemAdapter())
            .addDelegate(
                getDetailedAnswerUiItemAdapter(
                    detailedAnswerClickListener,
                    detailedAnswerTextChangedListener
                )
            )
            .addDelegate(getDetailedAnswerResultUiItemAdapter())
            .addDelegate(
                getDetailedAnswerValidationUiItemAdapted(
                    detailedAnswerValidationClickListener,
                    deleteValidationClickListener
                )
            )

            .addDelegate(getSuccessAnswerTitleUiItemAdapter())
            .addDelegate(getFavoritesAnswerWithErrorsResultUiItemAdapter())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionUiItem>() {
            override fun areItemsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                if (oldItem is SelectRightAnswerUiModel && newItem is SelectRightAnswerUiModel && oldItem.answer == newItem.answer) {
                    return true
                }
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SolutionUiItem, newItem: SolutionUiItem): Any? {
                return when {
                    oldItem is SelectRightAnswerUiModel && newItem is SelectRightAnswerUiModel && oldItem.isValidated != newItem.isValidated -> {
                        AnswerSelectRightPayload.Validated
                    }

                    oldItem is SelectRightAnswerUiModel && newItem is SelectRightAnswerUiModel && oldItem.checked != newItem.checked -> {
                        AnswerSelectRightPayload.Checked
                    }

                    oldItem is DetailedAnswerInputUiItem && newItem is DetailedAnswerInputUiItem -> {
                        true
                    }

                    oldItem is RightAnswerUiModel && newItem is RightAnswerUiModel -> {
                        true
                    }

                    oldItem is AnswerWithErrorsUiModel && newItem is AnswerWithErrorsUiModel -> {
                        true
                    }

                    oldItem is DetailedAnswerValidationUiItem && newItem is DetailedAnswerValidationUiItem -> {
                        return oldItem.isValidated != newItem.isValidated
                    }

                    oldItem is HeaderUiItem && newItem is HeaderUiItem -> {
                        return oldItem.isQuestionLikedByStudent != newItem.isQuestionLikedByStudent
                    }

                    else -> null
                }
            }
        }

        sealed interface AnswerSelectRightPayload {

            object Checked : AnswerSelectRightPayload

            object Validated : AnswerSelectRightPayload
        }
    }
}