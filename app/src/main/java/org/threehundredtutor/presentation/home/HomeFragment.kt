package org.threehundredtutor.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.databinding.SubjectFragmentBinding
import org.threehundredtutor.di.subject.HomeComponent
import org.threehundredtutor.presentation.common.ActionDialogFragment
import org.threehundredtutor.presentation.home.adapter.SubjectManager
import org.threehundredtutor.presentation.home.ui_models.SubjectTestUiModel

class HomeFragment : BaseFragment(R.layout.subject_fragment) {

    private val homeComponent by lazy {
        HomeComponent.createHomeComponent()
    }

    override var customHandlerBackStackWithDelay = true

    override val viewModel by viewModels<HomeViewModel> {
        homeComponent.viewModelMapFactory()
    }

    private val delegateAdapter: SubjectManager = SubjectManager(
        subjectClickListener = { subjectUiModel ->
            viewModel.onSubjectClicked(subjectUiModel)
        },
        subjectTestClickListener = { subjectTestUiModel ->
            viewModel.onSubjectTestClicked(subjectTestUiModel)
        }
    )

    private lateinit var binding: SubjectFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SubjectFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.subjectRecycler.adapter = delegateAdapter
    }

    override fun onObserveData() {
        viewModel.getUiItemStateFlow().observeFlow(this) { subjectUiItems ->
            delegateAdapter.items = subjectUiItems
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is HomeViewModel.UiEvent.NavigateSolution -> {
                    navigate(R.id.action_subjectFragment_to_solutionFragment, Bundle().apply {
                        putString(SUBJECT_TEST_KEY, state.subjectTestId)
                    })
                }

                is HomeViewModel.UiEvent.ShowDialogStartTest -> {
                    showActionDialogStartTest(state.subjectTestUiModel)
                }
            }
        }
    }

    private fun showActionDialogStartTest(subjectTestUiModel: SubjectTestUiModel) {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            positiveText = getString(R.string.start),
            title = getString(R.string.start_solution_test),
            message = subjectTestUiModel.subjectTestName,
            onPositiveClick = {
                viewModel.onDialogStartTestPositiveClicked(subjectTestUiModel.subjectTestId)
            },
        )
    }

    companion object {
        const val SUBJECT_TEST_KEY = "SUBJECT_TEST_KEY"
    }
}