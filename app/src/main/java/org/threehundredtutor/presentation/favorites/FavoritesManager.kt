package org.threehundredtutor.presentation.favorites

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getAnswerFavoritesSelectRightUiModelAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDividerUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getEmptyUiItem
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getFavoritesAnswerWithErrorsResultUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getFooterUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getHeaderUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getImageUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerTitleUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSeparatorUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSuccessAnswerTitleUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSupSubUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getTextUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getYoutubeUiItemAdapter
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel

class FavoritesManager(
    imageClickListener: (String) -> Unit,
    youtubeClickListener: (String) -> Unit,
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
            .addDelegate(getSelectRightAnswerTitleUiItemAdapter())
            .addDelegate(getAnswerFavoritesSelectRightUiModelAdapter())
            .addDelegate(getSuccessAnswerTitleUiItemAdapter())
            .addDelegate(getFavoritesAnswerWithErrorsResultUiItemAdapter())
            .addDelegate(getEmptyUiItem())
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
                    oldItem is HeaderUiItem && newItem is HeaderUiItem -> {
                        return oldItem.isQuestionLikedByStudent != newItem.isQuestionLikedByStudent
                    }

                    else -> null
                }
            }
        }
    }
}