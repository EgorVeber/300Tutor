package org.threehundredtutor.presentation.solution.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getResultAnswerDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getCheckButtonDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getDividerHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getFooterHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getImageHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getAnswerWithErrorsDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getDetailedAnswerDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getRightAnswerDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getResultButtonUiItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getSeparatorHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getAnswerXDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getSupSubHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getTextHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getTitleAnswerItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getTitleHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getYoutubeHtmlItemDelegate
import org.threehundredtutor.presentation.solution.models.answer.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.models.answer.DetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.models.answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.models.SolutionItem

class SolutionManager(
    imageClick: (String) -> Unit,
    answerWithErrorsClick: (AnswerWithErrorsUiModel, String) -> Unit,
    checkButtonAnswerClick: (RightAnswerUiModel, String) -> Unit,
    detailedAnswerClick: (DetailedAnswerUiModel, String) -> Unit,
    youtubeClick: (String) -> Unit,
    itemChecked: (String, String, Boolean) -> Unit,
    checkButtonSelectAnswerClick: (String) -> Unit,
) : AsyncListDifferDelegationAdapter<SolutionItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getTextHtmlItemDelegate())
            .addDelegate(getImageHtmlItemDelegate(imageClick))
            .addDelegate(getTitleHtmlItemDelegate())
            .addDelegate(getDividerHtmlItemDelegate())
            .addDelegate(getSupSubHtmlItemDelegate())
            .addDelegate(getFooterHtmlItemDelegate())
            .addDelegate(getAnswerWithErrorsDelegate(answerWithErrorsClick))
            .addDelegate(getDetailedAnswerDelegate(detailedAnswerClick))
            .addDelegate(getRightAnswerDelegate(checkButtonAnswerClick))
            .addDelegate(getAnswerXDelegate(itemChecked))
            .addDelegate(getSeparatorHtmlItemDelegate())
            .addDelegate(getYoutubeHtmlItemDelegate(youtubeClick))
            .addDelegate(getResultAnswerDelegate())
            .addDelegate(getCheckButtonDelegate(checkButtonSelectAnswerClick))
            .addDelegate(getResultButtonUiItemDelegate())
            .addDelegate(getTitleAnswerItemDelegate())
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionItem>() {
            override fun areItemsTheSame(oldItem: SolutionItem, newItem: SolutionItem): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(oldItem: SolutionItem, newItem: SolutionItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}