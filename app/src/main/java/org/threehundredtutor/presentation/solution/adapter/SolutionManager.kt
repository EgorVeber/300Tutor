package org.threehundredtutor.presentation.solution.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getAnswerSelectRightUiModelAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getAnswerWithErrorsResultUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getAnswerWithErrorsUiModelAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerResultUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDetailedAnswerValidationUiItemAdapted
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDividerUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getFooterUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getHeaderUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getImageUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getResultButtonUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getRightAnswerResultUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getRightAnswerUiModelAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerCheckButtonUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerTitleUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSeparatorUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSupSubUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getTextUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getYoutubeUiItemAdapter
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.AnswerSelectRightUiModel

class SolutionManager(
    imageClickListener: (String) -> Unit,
    youtubeClickListener: (String) -> Unit,
    selectRightAnswerClickListener: (String, String, Boolean) -> Unit,
    selectRightAnswerCheckButtonClickListener: (String) -> Unit,
    rightAnswerClickListener: (RightAnswerUiModel, String) -> Unit,
    answerWithErrorsClickListener: (AnswerWithErrorsUiModel, String) -> Unit,
    detailedAnswerClickListener: (DetailedAnswerUiItem, String) -> Unit,
    detailedAnswerTextChangedListener: (DetailedAnswerUiItem, String) -> Unit,
    detailedAnswerValidationClickListener: (DetailedAnswerValidationUiItem, String) -> Unit,
    deleteValidationClickListener: (DetailedAnswerValidationUiItem) -> Unit,
) : AsyncListDifferDelegationAdapter<SolutionUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getHeaderUiItemAdapter())
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
            .addDelegate(getRightAnswerUiModelAdapter(rightAnswerClickListener))
            .addDelegate(getRightAnswerResultUiItemAdapter())
            .addDelegate(getAnswerWithErrorsUiModelAdapter(answerWithErrorsClickListener))
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
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionUiItem>() {
            override fun areItemsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SolutionUiItem, newItem: SolutionUiItem): Any? {
                return if (oldItem is AnswerSelectRightUiModel && newItem is AnswerSelectRightUiModel && oldItem.checked != newItem.checked) {
                    true
                } else if (oldItem is DetailedAnswerUiItem && newItem is DetailedAnswerUiItem) {
                    true
                } else null
            }
        }
    }
}