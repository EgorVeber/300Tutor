package com.v_points.app.presentation.subject_detailed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.subject_detailed.SubjectDetailedComponent
import com.v_points.app.presentation.subject_detailed.adapter.SubjectDetailedManager
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_WORKSPACE_ID_KEY
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.util_class.BundleParcelable
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.databinding.SubjectDetailedFragmentBinding

class SubjectDetailedFragment : BaseFragment(UiCoreLayout.subject_detailed_fragment) {

    override val bottomMenuVisible: Boolean = false

    override val viewModel: SubjectDetailedViewModel by viewModels {
        subjectDetailedComponent.viewModelMapFactory()
    }

    private lateinit var binding: SubjectDetailedFragmentBinding
    private val subjectId by BundleString(SUBJECT_DETAILED_KEY_ID, EMPTY_STRING)
    private val subjectName by BundleString(SUBJECT_DETAILED_KEY_NAME, EMPTY_STRING)
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
                            putString(SUBJECT_DETAILED_KEY_ID, "")
                            putString(SUBJECT_DETAILED_KEY_NAME, state.title)
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

                is SubjectDetailedViewModel.UiEvent.NavigateToWorkspace -> {
                    navigate(
                        resId = R.id.action_subjectDetailedFragment_to_subjectWorkspaceFragment,
                        bundle = Bundle().apply {
                            putString(
                                SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY,
                                state.subjectName
                            )
                            putString(SUBJECT_WORKSPACE_WORKSPACE_ID_KEY, state.workspaceId)
                            putString(
                                SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME,
                                state.workspaceName
                            )
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

        const val SUBJECT_DETAILED_KEY_ID = "SUBJECT_DETAILED_KEY_ID"
        const val SUBJECT_DETAILED_KEY_NAME = "SUBJECT_DETAILED_KEY_NAME"
    }
}
