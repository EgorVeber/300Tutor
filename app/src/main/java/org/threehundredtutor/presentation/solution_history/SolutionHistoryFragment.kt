package org.threehundredtutor.presentation.solution_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.setDebouncedClickListener
import org.threehundredtutor.databinding.FragmentSolutionHistoryBinding
import org.threehundredtutor.di.solution_history.SolutionHistoryComponent
import org.threehundredtutor.presentation.common.ActionDialogFragment
import org.threehundredtutor.presentation.common.LoadingDialog
import org.threehundredtutor.presentation.solution_history.adapter.SolutionHistoryAdapters.DIFF_CALLBACK
import org.threehundredtutor.presentation.solution_history.adapter.SolutionHistoryAdapters.getSolutionHistoryUiItemAdapter

class SolutionHistoryFragment : BaseFragment(R.layout.fragment_solution_history) {
    private lateinit var binding: FragmentSolutionHistoryBinding

    override var customHandlerBackStack = true

    private val solutionHistoryComponent by lazy {
        SolutionHistoryComponent.createSolutionHistoryComponent()
    }

    override val viewModel by viewModels<SolutionHistoryViewModel> {
        solutionHistoryComponent.viewModelMapFactory()
    }

    private val delegateAdapter =
        AsyncListDifferDelegationAdapter(
            DIFF_CALLBACK,
            getSolutionHistoryUiItemAdapter { id ->
                viewModel.onGoSolutionClickListener(id)
            }
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
                is SolutionHistoryViewModel.UiEvent.NavigateToSolution -> navigateSolutionDetailedScreen(
                    state.solutionId
                )

                is SolutionHistoryViewModel.UiEvent.ShowDialogSolution -> showActionDialogOpenSolution(
                    state.solutionId
                )
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

    private fun showActionDialogOpenSolution(solutionId: String) {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            positiveText = getString(R.string.go),
            message = getString(R.string.go_to_test_solution),
            onPositiveClick = { viewModel.onDialogOkClicked(solutionId) },
        )
    }

    private fun navigateSolutionDetailedScreen(solutionId: String) {
        navigate(R.id.action_solutionHistoryFragment_to_solutionFragment, Bundle().apply
        {
            putString(SOLUTION_TEST_KEY, solutionId)
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

    companion object {
        const val SOLUTION_TEST_KEY = "SOLUTION_TEST_KEY"
    }
}