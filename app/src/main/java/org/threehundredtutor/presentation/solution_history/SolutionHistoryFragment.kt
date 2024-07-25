package org.threehundredtutor.presentation.solution_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.solution_history.SolutionHistoryComponent
import org.threehundredtutor.presentation.solution.SolutionFragment.Companion.SOLUTION_SOLUTION_ID_KEY
import org.threehundredtutor.presentation.solution_history.adapter.SolutionHistoryManager
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.view_components.setDebouncedClickListener
import org.threehundredtutor.ui_core.databinding.FragmentSolutionHistoryBinding

class SolutionHistoryFragment : BaseFragment(UiCoreLayout.fragment_solution_history) {
    private lateinit var binding: FragmentSolutionHistoryBinding

    override var customHandlerBackStack = true

    private val solutionHistoryComponent by lazy {
        SolutionHistoryComponent.createSolutionHistoryComponent()
    }

    override val viewModel by viewModels<SolutionHistoryViewModel> {
        solutionHistoryComponent.viewModelMapFactory()
    }

    private val delegateAdapter = SolutionHistoryManager(
        solutionHistoryClickListener = { solutionId, isFinished ->
            viewModel.onSolutionHistoryClicked(solutionId)
        },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSolutionHistoryBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onObserveData() {
        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }
        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SolutionHistoryViewModel.UiEvent.NavigateToSolution ->
                    navigateToSolutionFragment(state.solutionId)
            }
        }

        viewModel.getLoadingStateFlow().observeFlow(this) { loading ->
            showLoadingDialog(loading)
        }
    }

    private fun showLoadingDialog(loading: Boolean) {
        if (loading) {
            LoadingDialog.show(requireActivity().supportFragmentManager)
        } else {
            LoadingDialog.close(requireActivity().supportFragmentManager)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onBackPressed() {
        navigate(R.id.action_solutionHistoryFragment_to_homeFragment)
    }

    private fun navigateToSolutionFragment(solutionId: String) {
        navigate(R.id.action_solutionHistoryFragment_to_solutionFragment, Bundle().apply
        {
            putString(SOLUTION_SOLUTION_ID_KEY, solutionId)
        })
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.recyclerSolutionHistory.adapter = delegateAdapter
        binding.completedSolution.setDebouncedClickListener {
            viewModel.onCompletedChipClicked()
        }
        binding.allSolution.setDebouncedClickListener {
            viewModel.onAllChipClicked()
        }
        binding.notCompletedSolution.setDebouncedClickListener {
            viewModel.onNotCompletedChipClicked()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
    }

    override fun onDestroyView() {
        //  TODO потом обдумать поведение.
        LoadingDialog.close(requireActivity().supportFragmentManager)
        super.onDestroyView()
    }
}