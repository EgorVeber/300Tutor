package org.threehundredtutor.presentation.solution_history.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.databinding.SolutionHistoryItemBinding
import org.threehundredtutor.presentation.solution_history.SolutionHistoryUiModel

object SolutionHistoryAdapters {
    fun getSolutionHistoryUiItemAdapter(goSolutionClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<SolutionHistoryUiModel, SolutionHistoryUiModel, SolutionHistoryItemBinding>(
            { layoutInflater, root ->
                SolutionHistoryItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.goButton.setOnClickListener {
                goSolutionClickListener.invoke(item.id)
            }
            bind {
                with(binding) {
                    nameTestTv.text = item.nameTest
                    startTestDateTv.text = item.startTestDate
                    endTestDateTv.text = item.endTestDate
                    groupNotFinished.isVisible = item.isFinished
                    pointWithTestTv.text = item.pointWithTest
                    testIsSolvedPercentTv.text = item.testIsSolvedPercent
                    issuesResolvedPercent.text = item.issuesResolvedPercent
                }
            }
        }

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionHistoryUiModel>() {
        override fun areItemsTheSame(
            oldItem: SolutionHistoryUiModel,
            newItem: SolutionHistoryUiModel
        ): Boolean {
            return oldItem.nameTest == newItem.nameTest
        }

        override fun areContentsTheSame(
            oldItem: SolutionHistoryUiModel,
            newItem: SolutionHistoryUiModel
        ): Boolean {
            return oldItem.equals(newItem)
        }
    }
}