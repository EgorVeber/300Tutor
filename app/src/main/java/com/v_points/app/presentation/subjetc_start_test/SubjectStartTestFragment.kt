package com.v_points.app.presentation.subjetc_start_test

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.test.SubjectStartTestComponent
import com.v_points.app.presentation.solution.SolutionFragment.Companion.SOLUTION_SOLUTION_ID_KEY
import com.v_points.app.presentation.solution.SolutionFragment.Companion.SOLUTION_TEST_ID_KEY
import com.v_points.app.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_SUBJECT_NAME_KEY
import com.v_points.app.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_ID_KEY
import com.v_points.app.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_NAME_KEY
import com.v_points.app.presentation.subject_tests.SubjectTestsFragment.Companion.SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY
import com.v_points.app.presentation.subjetc_start_test.adapter.SubjectStartTestManager
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.util_class.BundleInt
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.databinding.TestFragmentBinding

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

    private val delegateAdapter = SubjectStartTestManager(
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
