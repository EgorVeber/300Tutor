package org.threehundredtutor.presentation.subject_tests

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.utils.BundleString
import org.threehundredtutor.databinding.SubjectTestsFragmentBinding
import org.threehundredtutor.di.subject_tests.SubjectTestsComponent
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_MENU_ITEM_NAME
import org.threehundredtutor.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_SUBJECT_ID
import org.threehundredtutor.presentation.subject_detailed.SubjectDetailedFragment.Companion.SUBJECT_TESTS_SUBJECT_NAME
import org.threehundredtutor.presentation.subject_tests.adapter.SubjectTestsManager
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestsUiItem

class SubjectTestsFragment : BaseFragment(R.layout.subject_tests_fragment) {

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
    }
}
