package org.threehundredtutor.presentation.solution.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getAnswerHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getDividerHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getFooterHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getImageHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getQuestionAnswerWithErrorsHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getQuestionDetailedAnswerHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getQuestionRightAnswerHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getSelectRightAnswerOrAnswersUiModelHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getSeparatorHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getSupSubHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getTextHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getTitleHtmlItemDelegate
import org.threehundredtutor.presentation.solution.adapter.SolutionDelegate.getYoutubeHtmlItemDelegate
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.QuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.model.QuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.model.QuestionRightAnswerUiModel
import org.threehundredtutor.presentation.solution.model.SelectRightAnswerOrAnswersUiModel

class SolutionManager(
    imageClick: (String) -> Unit,
    answerWithErrorsClick: (QuestionAnswerWithErrorsUiModel, String) -> Unit,
    rightAnswerWithErrorsClick: (QuestionRightAnswerUiModel, String) -> Unit,
    detailedAnswerClick: (QuestionDetailedAnswerUiModel, String) -> Unit,
    selectRightAnswerOrAnswersClick: (SelectRightAnswerOrAnswersUiModel) -> Unit,
    itemChecked: (String, Boolean, String) -> Unit,
    youtubeClick: (String) -> Unit,
) : AsyncListDifferDelegationAdapter<HtmlItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getTextHtmlItemDelegate())
            .addDelegate(getImageHtmlItemDelegate(imageClick))
            .addDelegate(getTitleHtmlItemDelegate())
            .addDelegate(getDividerHtmlItemDelegate())
            .addDelegate(getSupSubHtmlItemDelegate())
            .addDelegate(getFooterHtmlItemDelegate())
            .addDelegate(getQuestionAnswerWithErrorsHtmlItemDelegate(answerWithErrorsClick))
            .addDelegate(getQuestionDetailedAnswerHtmlItemDelegate(detailedAnswerClick))
            .addDelegate(getQuestionRightAnswerHtmlItemDelegate(rightAnswerWithErrorsClick))
            .addDelegate(
                getSelectRightAnswerOrAnswersUiModelHtmlItemDelegate(
                    selectRightAnswerOrAnswersClick,
                    itemChecked
                )
            )
            .addDelegate(getSeparatorHtmlItemDelegate())
            .addDelegate(getYoutubeHtmlItemDelegate(youtubeClick))
            .addDelegate(getAnswerHtmlItemDelegate())
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HtmlItem>() {
            override fun areItemsTheSame(oldItem: HtmlItem, newItem: HtmlItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HtmlItem, newItem: HtmlItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}