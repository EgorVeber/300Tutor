package org.threehundredtutor.presentation.subject_detailed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.utils.BundleParcelable
import org.threehundredtutor.common.utils.BundleString
import org.threehundredtutor.databinding.SubjectDetailedFragmentBinding
import org.threehundredtutor.di.subject_detailed.SubjectDetailedComponent
import org.threehundredtutor.presentation.main.MainFragment
import org.threehundredtutor.presentation.subject_detailed.adapter.SubjectDetailedManager
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel

class SubjectDetailedFragment : BaseFragment(R.layout.subject_detailed_fragment) {

    override val bottomMenuVisible: Boolean = false

    override val viewModel: SubjectDetailedViewModel by viewModels {
        subjectDetailedComponent.viewModelMapFactory()
    }

    private lateinit var binding: SubjectDetailedFragmentBinding
    private val subjectId by BundleString(MainFragment.SUBJECT_DETAILED_KEY_ID, EMPTY_STRING)
    private val subjectName by BundleString(MainFragment.SUBJECT_DETAILED_KEY_NAME, EMPTY_STRING)
    private val menuItem by BundleParcelable(
        SUBJECT_DETAILED_KEY_MENU_ITEMS, SubjectMenuItemUiModel.EMPTY
    )

    private val delegateAdapter: SubjectDetailedManager = SubjectDetailedManager(
        subjectClickListener = { subjectDetailedMenuItemUiModel: SubjectMenuItemUiModel ->
            viewModel.onMenuItemClicked(subjectDetailedMenuItemUiModel)
        },
    )

    private val subjectDetailedComponent by lazy {
        SubjectDetailedComponent.createSubjectDetailedComponent(
            SubjectDetailedBundleModel(
                subjectId = subjectId,
                subjectName = subjectName,
                menuItem = menuItem
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SubjectDetailedFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.subjectDetailedRecycler.adapter = delegateAdapter
        binding.subjectDetailedToolBar.title = subjectName
        binding.subjectDetailedToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onObserveData() {
        viewModel.getLoadingState().observeFlow(this) { visibility ->
            binding.progressContainer.isVisible = visibility
        }

        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SubjectDetailedViewModel.UiEvent.NavigateToMenuItem -> {
                    navigate(
                        R.id.action_subjectDetailedFragment_to_subjectDetailedFragment,
                        Bundle().apply {
                            putString(MainFragment.SUBJECT_DETAILED_KEY_ID, "")
                            putString(MainFragment.SUBJECT_DETAILED_KEY_NAME, state.title)
                            putParcelable(SUBJECT_DETAILED_KEY_MENU_ITEMS, state.menuItem)
                        })
                }

                is SubjectDetailedViewModel.UiEvent.NavigateToSubjectTests -> {
                    navigate(
                        resId = R.id.action_subjectDetailedFragment_to_subjectTestsFragment,
                        bundle = Bundle().apply {
                            putString(SUBJECT_TESTS_SUBJECT_ID, state.subjectId)
                            putString(SUBJECT_TESTS_SUBJECT_NAME, state.subjectName)
                            putString(SUBJECT_TESTS_MENU_ITEM_NAME, state.subjectMenuItemName)
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val SUBJECT_DETAILED_KEY_MENU_ITEMS = "SUBJECT_DETAILED_KEY_MENU_ITEMS"
        const val SUBJECT_TESTS_SUBJECT_ID = "SUBJECT_TESTS_SUBJECT_ID"
        const val SUBJECT_TESTS_SUBJECT_NAME = "SUBJECT_TESTS_SUBJECT_NAME"
        const val SUBJECT_TESTS_MENU_ITEM_NAME = "SUBJECT_TESTS_MENU_ITEM_NAME"
    }
}
