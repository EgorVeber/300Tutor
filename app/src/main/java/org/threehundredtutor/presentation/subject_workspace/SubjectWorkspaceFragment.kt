package org.threehundredtutor.presentation.subject_workspace

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.subject_workspase.SubjectWorkspaceComponent
import org.threehundredtutor.presentation.html_page.HtmlPageFragment
import org.threehundredtutor.presentation.subject_workspace.adapter.WorkspaceDirectoryManager
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.UiCoreStrings
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.SubjectWorkspaceFragmentBinding

class SubjectWorkspaceFragment : BaseFragment(UiCoreLayout.subject_workspace_fragment) {

    override val bottomMenuVisible: Boolean = false

    override val viewModel: SubjectWorkspaceViewModel by viewModels {
        subjectWorkspaceComponent.viewModelMapFactory()
    }

    private lateinit var binding: SubjectWorkspaceFragmentBinding

    private val workspaceId by BundleString(
        SUBJECT_WORKSPACE_WORKSPACE_ID_KEY,
        EMPTY_STRING
    )

    private val directoryId by BundleString(
        SUBJECT_WORKSPACE_DIRECTORY_ID_KEY,
        EMPTY_STRING
    )

    private val titleSubjectOrDirectory by BundleString(
        SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY,
        EMPTY_STRING
    )

    private val subTitleWorkSpaceOrDirectory by BundleString(
        SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME,
        EMPTY_STRING
    )

    private val delegateAdapter: WorkspaceDirectoryManager = WorkspaceDirectoryManager(
        workspaceDirectoryUiModelClickListener = { directoryId ->
            viewModel.onDirectoryClicked(directoryId)
        },
    )

    private val subjectWorkspaceComponent by lazy {
        SubjectWorkspaceComponent.createSubjectWorkspaceComponent(
            SubjectWorkspaceBundleModel(
                workspaceId = workspaceId,
                directoryId = directoryId,
                subTitleWorkSpaceOrDirectory = subTitleWorkSpaceOrDirectory
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SubjectWorkspaceFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.subjectWorkspaceRecycler.adapter = delegateAdapter
        binding.subjectWorkspaceToolBar.title = titleSubjectOrDirectory
        binding.subjectWorkspaceToolBar.subtitle = subTitleWorkSpaceOrDirectory
        binding.subjectWorkspaceToolBar.setNavigationOnClickListener {
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
                is SubjectWorkspaceViewModel.UiEvent.NavigateToDirectory -> {
                    navigate(
                        resId = R.id.action_subjectWorkspaceFragment_to_subjectWorkspaceFragment,
                        bundle = Bundle().apply {
                            putString(
                                SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY,
                                state.subTitleWorkSpaceOrDirectory
                            )
                            putString(
                                SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME,
                                state.subTitleDirectoryName
                            )
                            putString(
                                SUBJECT_WORKSPACE_DIRECTORY_ID_KEY,
                                state.directoryId
                            )
                        }
                    )
                }

                is SubjectWorkspaceViewModel.UiEvent.NavigateToHtmlPage -> {
                    navigate(
                        resId = R.id.action_subjectWorkspaceFragment_to_htmlPageFragment,
                        bundle = Bundle().apply {
                            putString(
                                HtmlPageFragment.HTML_PAGE_TITLE_KEY,
                                subTitleWorkSpaceOrDirectory
                            )
                            putString(
                                HtmlPageFragment.HTML_PAGE_SUBTITLE_KEY,
                                state.subTitleDirectoryName
                            )
                            putString(
                                HtmlPageFragment.HTML_PAGE_DIRECTORY_ID_KEY,
                                state.directoryId
                            )
                        }
                    )
                }

                SubjectWorkspaceViewModel.UiEvent.ShowErrorFind -> {
                    showSnack(getString(UiCoreStrings.system_not_found))
                }
            }
        }
    }

    companion object {
        //Outher
        const val SUBJECT_WORKSPACE_WORKSPACE_ID_KEY = "SUBJECT_WORKSPACE_WORKSPACE_ID_KEY"
        const val SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME =
            "SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME"
        const val SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY =
            "SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY"

        //Inner
        private const val SUBJECT_WORKSPACE_DIRECTORY_ID_KEY = "SUBJECT_WORKSPACE_DIRECTORY_ID_KEY"
    }
}
