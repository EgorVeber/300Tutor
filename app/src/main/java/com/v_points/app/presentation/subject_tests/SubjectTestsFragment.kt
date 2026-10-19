package com.v_points.app.presentation.subject_tests

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.subject_tests.SubjectTestsComponent
import com.v_points.app.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_MENU_ITEM_NAME
import com.v_points.app.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_SUBJECT_ID
import com.v_points.app.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_SUBJECT_NAME
import com.v_points.app.presentation.subject_tests.adapter.SubjectTestsManager
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestUiModel
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestsUiItem
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.databinding.SubjectTestsFragmentBinding

class SubjectTestsFragment : BaseFragment(UiCoreLayout.subject_tests_fragment) {

    private lateinit var binding: SubjectTestsFragmentBinding

    private val subjectTestsComponent by lazy {
        SubjectTestsComponent.createSubjectTestsComponent(subjectId)
    }

    override val viewModel: SubjectTestsViewModel by viewModels {
        subjectTestsComponent.viewModelMapFactory()
    }

    private val subjectId by BundleString(SUBJECT_TESTS_SUBJECT_ID, EMPTY_STRING)
    private val subjectName by BundleString(SUBJECT_TESTS_SUBJECT_NAME, EMPTY_STRING)
    private val subjectMenuItemName by BundleString(SUBJECT_TESTS_MENU_ITEM_NAME, EMPTY_STRING)

    private val delegateAdapter: SubjectTestsManager = SubjectTestsManager(
        subjectClickListener = { subjectTestUiModel: SubjectTestUiModel ->
            viewModel.onTestClicked(subjectTestUiModel)
        },
    )

    override val bottomMenuVisible: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SubjectTestsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.subjectDetailedRecycler.adapter = delegateAdapter
        with(binding.subjectTestsToolBar) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            title = subjectName
            subtitle = subjectMenuItemName
        }
    }

    override fun onObserveData() {
        viewModel.getLoadingState().observeFlow(this) { visibility ->
            binding.progressContainer.isVisible = visibility
        }
        viewModel.getUiItemStateFlow()
            .observeFlow(this) { subjectTestsItems: List<SubjectTestsUiItem> ->
                delegateAdapter.items = subjectTestsItems
            }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SubjectTestsViewModel.UiEvent.NavigateToTest -> {
                    navigate(R.id.action_subjectTestsFragment_to_testFragment,
                        Bundle().apply {
                            putString(SUBJECT_TESTS_TEST_ID_KEY, state.subjectTestId)
                            putString(SUBJECT_TESTS_SUBJECT_NAME_KEY, subjectName)
                            putString(SUBJECT_TESTS_TEST_NAME_KEY, state.subjectTestName)
                            putInt(SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY, state.questionsCount)
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val SUBJECT_TESTS_TEST_ID_KEY = "SUBJECT_TESTS_TEST_ID_KEY"
        const val SUBJECT_TESTS_TEST_NAME_KEY = "SUBJECT_TESTS_TEST_NAME_KEY"
        const val SUBJECT_TESTS_SUBJECT_NAME_KEY = "SUBJECT_TESTS_SUBJECT_NAME_KEY"
        const val SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY = "SUBJECT_TESTS_TEST_QUESTION_COUNT_KEY"
    }
}
