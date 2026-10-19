package com.v_points.app.presentation.html_page


import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreDimens
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.html_page.HtmlPageComponent
import com.v_points.app.presentation.html_page.adapter.HtmlPageManager
import com.v_points.app.presentation.solution.PhotoDetailsFragment
import com.v_points.app.presentation.solution.SolutionFragment
import com.v_points.app.presentation.solution.adapter.SolutionItemDecoration
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.openYoutubeLink
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.databinding.HtmlPageFragmentBinding


class HtmlPageFragment : BaseFragment(UiCoreLayout.html_page_fragment) {

    override val bottomMenuVisible: Boolean = false

    override val viewModel: HtmlPageViewModel by viewModels {
        hmlPageComponent.viewModelMapFactory()
    }

    private lateinit var binding: HtmlPageFragmentBinding

    private val directoryId by BundleString(HTML_PAGE_DIRECTORY_ID_KEY, EMPTY_STRING)
    private val title by BundleString(HTML_PAGE_TITLE_KEY, EMPTY_STRING)
    private val subTitle by BundleString(HTML_PAGE_SUBTITLE_KEY, EMPTY_STRING)

    private val hmlPageComponent by lazy {
        HtmlPageComponent.createHtmlPageComponent(directoryId)
    }

    private val delegateAdapter: HtmlPageManager = HtmlPageManager(
        imageClickListener = { imageId ->
            viewModel.onImageClicked(imageId)
        },
        youtubeClickListener = { link -> openYoutubeLink(link) },
        firstStartTestClickListener = { viewModel.onFirstStartTestClicked() },
        secondStartTestClickListener = { viewModel.onSecondStartTestClicked() },
        fullStartTestClickListener = { viewModel.onFullStartTestClicked() },
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = HtmlPageFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.recyclerHtmlPage.adapter = delegateAdapter
        binding.recyclerHtmlPage.addItemDecoration(
            SolutionItemDecoration(
                paddingItems = resources.getDimension(
                    UiCoreDimens.solution_item_space_horizontal
                )
            )
        )
        binding.htmlPageToolbar.title = title
        binding.htmlPageToolbar.subtitle = subTitle
        binding.htmlPageToolbar.setNavigationOnClickListener {
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

        viewModel.getMessagesStream().observeFlow(this) { message ->
            //  showMessage(message)
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { uiEvent ->
            when (uiEvent) {
                is HtmlPageViewModel.UiEvent.NavigatePhotoDetailed -> {
                    PhotoDetailsFragment.showDialog(childFragmentManager, uiEvent.imagePath)
                }

                is HtmlPageViewModel.UiEvent.OpenYoutube -> {
                    openYoutubeLink(uiEvent.link)
                }

                is HtmlPageViewModel.UiEvent.NavigateToSolution -> {
                    navigate(R.id.action_htmlPageFragment_to_solutionFragment, Bundle().apply
                    {
                        putString(
                            SolutionFragment.SOLUTION_HTML_PAGE_DIRECTORY_ID_KEY,
                            uiEvent.directoryId
                        )
                        putString(
                            SolutionFragment.SOLUTION_HTML_PAGE_WORKSPACE_ID_KEY,
                            uiEvent.workspaceId
                        )
                        putSerializable(
                            SolutionFragment.SOLUTION_HTML_PAGE_TEST_TYPE_ID_KEY,
                            uiEvent.htmlPageTestType
                        )
                    })
                }
            }
        }
    }

    companion object {
        const val HTML_PAGE_TITLE_KEY = "HTML_PAGE_TITLE_KEY"
        const val HTML_PAGE_SUBTITLE_KEY = "HTML_PAGE_SUBTITLE_KEY"
        const val HTML_PAGE_DIRECTORY_ID_KEY = "HTML_PAGE_DIRECTORY_ID_KEY"
    }
}
