package org.threehundredtutor.presentation.solution_history.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiItem
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiModel
import org.threehundredtutor.presentation.solution_history.models.StartTestUiModel
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_core.databinding.SolutionHistoryItemBinding
import org.threehundredtutor.ui_core.databinding.TestStartItemBinding

object SolutionHistoryAdapters {
    fun getSolutionHistoryUiModelAdapter(solutionHistoryClickListener: (String, Boolean) -> Unit) =
        adapterDelegateViewBinding<SolutionHistoryUiModel, SolutionHistoryUiItem, SolutionHistoryItemBinding>(
            { layoutInflater, root ->
                SolutionHistoryItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.goButton.setOnClickListener {
                solutionHistoryClickListener.invoke(item.solutionId, item.isFinished)
            }
            bind {
                with(binding) {
                    if (item.isFinished) {
                        binding.goButton.text = getString(UiCoreStrings.go)
                        binding.questionCountTv.isVisible = false
                    } else {
                        binding.goButton.text = getString(UiCoreStrings.continue_action)
                        if (item.questionsCount != DEFAULT_NOT_VALID_VALUE_INT) {
                            binding.questionCountTv.text =
                                getString(
                                    UiCoreStrings.question_count,
                                    item.questionsCount.toString()
                                )
                            binding.questionCountTv.isVisible = true
                        }
                    }
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

    fun getStartTestUiModelAdapter(startTestClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<StartTestUiModel, SolutionHistoryUiItem, TestStartItemBinding>(
            { layoutInflater, root ->
                TestStartItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.startButton.setOnClickListener {
                startTestClickListener.invoke(item.testId)
            }
            bind {
                binding.nameTestTv.text = item.nameTest
                binding.questionCountTv.text =
                    getString(UiCoreStrings.question_count, item.questionsCount.toString())
            }
        }
}