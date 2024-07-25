package org.threehundredtutor.presentation.subjetc_start_test.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution_history.adapter.SolutionHistoryAdapters.getSolutionHistoryUiModelAdapter
import org.threehundredtutor.presentation.solution_history.adapter.SolutionHistoryAdapters.getStartTestUiModelAdapter
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiItem

class SubjectStartTestManager(
    solutionHistoryClickListener: (String, Boolean) -> Unit,
    startTestClickListener: (String) -> Unit
) : AsyncListDifferDelegationAdapter<SolutionHistoryUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(getSolutionHistoryUiModelAdapter(solutionHistoryClickListener))
        delegatesManager.addDelegate(getStartTestUiModelAdapter(startTestClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionHistoryUiItem>() {
            override fun areItemsTheSame(
                oldItem: SolutionHistoryUiItem,
                newItem: SolutionHistoryUiItem
            ): Boolean = oldItem.javaClass == newItem.javaClass

            override fun areContentsTheSame(
                oldItem: SolutionHistoryUiItem,
                newItem: SolutionHistoryUiItem
            ): Boolean = oldItem.equals(newItem)
        }
    }
}