package org.threehundredtutor.presentation.subjetc_start_test

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.test.SubjectStartTestComponent
import org.threehundredtutor.presentation.solution.SolutionFragment.Companion.SOLUTION_SOLUTION_ID_KEY
import org.threehundredtutor.presentation.solution.SolutionFragment.Companion.SOLUTION_TEST_ID_KEY
import org.threehundredtutor.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_SUBJECT_NAME_KEY
import org.threehundredtutor.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_ID_KEY
import org.threehundredtutor.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_NAME_KEY
import org.threehundredtutor.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY
import org.threehundredtutor.presentation.subjetc_start_test.adapter.SubjectStatrTestManager
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.util_class.BundleInt
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.TestFragmentBinding

class SubjectStartTestFragment : BaseFragment(UiCoreLayout.test_fragment) {

    private lateinit var binding: TestFragmentBinding

    private val subjectStartTestComponent by lazy {
        SubjectStartTestComponent.createTestComponent(
            SubjectStartTestBundleModel(
                testId = testId,
                testName = testName,
                questionCount = questionCount,
            )
        )
    }

    override val viewModel: SubjectStartTestViewModel by viewModels {
        subjectStartTestComponent.viewModelMapFactory()
    }

    private val testId by BundleString(SUBJECT_TESTS_TEST_ID_KEY, EMPTY_STRING)
    private val subjectName by BundleString(SUBJECT_TESTS_SUBJECT_NAME_KEY, EMPTY_STRING)
    private val testName by BundleString(SUBJECT_TESTS_TEST_NAME_KEY, EMPTY_STRING)
    private val questionCount by BundleInt(
        SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY,
        DEFAULT_NOT_VALID_VALUE_INT
    )

    private val delegateAdapter = SubjectStatrTestManager(
        solutionHistoryClickListener = { solutionId, _ ->
            viewModel.onSolutionHistoryClicked(solutionId)
        },
        startTestClickListener = { testId ->
            viewModel.onStartTestClicked(testId)
        }
    )

    override val bottomMenuVisible: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TestFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.testRecycler.adapter = delegateAdapter
        with(binding.testToolBar) {
            title = subjectName
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.onViewInitiated()
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
    }

    override fun onObserveData() {
        viewModel.getLoadingState().observeFlow(this) { visibility ->
            binding.progressContainer.isVisible = visibility
            if (!visibility) binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SubjectStartTestViewModel.UiEvent.NavigateToSolutionLoadSolution -> {
                    navigate(R.id.action_testFragment_to_solutionFragment, Bundle().apply
                    {
                        putString(SOLUTION_SOLUTION_ID_KEY, state.solutionId)
                    })
                }

                is SubjectStartTestViewModel.UiEvent.NavigateToSolutionStartTest -> {
                    navigate(R.id.action_testFragment_to_solutionFragment, Bundle().apply
                    {
                        putString(SOLUTION_TEST_ID_KEY, state.testId)
                    })
                }
            }
        }
    }
}