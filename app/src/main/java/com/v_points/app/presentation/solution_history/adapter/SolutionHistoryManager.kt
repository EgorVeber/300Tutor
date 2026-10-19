package com.v_points.app.presentation.solution_history.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.v_points.app.presentation.solution_history.adapter.SolutionHistoryAdapters.getSolutionHistoryUiModelAdapter
import com.v_points.app.presentation.solution_history.models.SolutionHistoryUiItem

class SolutionHistoryManager(
    solutionHistoryClickListener: (String, Boolean) -> Unit,
) : AsyncListDifferDelegationAdapter<SolutionHistoryUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(
            getSolutionHistoryUiModelAdapter(
                solutionHistoryClickListener
            )
        )
        delegatesManager.addDelegate(SolutionHistoryAdapters.getEmptyHistoryUiItem())
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