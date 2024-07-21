package org.threehundredtutor.presentation.html_page


import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreDimens
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.html_page.HtmlPageComponent
import org.threehundredtutor.presentation.html_page.adapter.HtmlPageManager
import org.threehundredtutor.presentation.solution.PhotoDetailsFragment
import org.threehundredtutor.presentation.solution.SolutionFragment
import org.threehundredtutor.presentation.solution.adapter.SolutionItemDecoration
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.openYoutubeLink
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.HtmlPageFragmentBinding


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
                    navigate(R.id.action_htmlPageFragment_to_photoDetailsFragment, Bundle().apply {
                        putString(PhotoDetailsFragment.PHOTO_DETAILED_IMAGE_PATH, uiEvent.imagePath)
                    })
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
