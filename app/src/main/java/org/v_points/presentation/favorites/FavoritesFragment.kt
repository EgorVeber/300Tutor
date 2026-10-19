package org.v_points.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.v_points.core.UiCoreDimens
import org.v_points.core.UiCoreLayout
import org.v_points.di.favorites.FavoritesComponent
import org.v_points.presentation.solution.PhotoDetailsFragment
import org.v_points.presentation.solution.adapter.SolutionItemDecoration
import org.v_points.ui_common.flow.observeFlow
import org.v_points.ui_common.fragment.base.BaseFragment
import org.v_points.ui_common.fragment.openYoutubeLink
import org.v_points.ui_common.fragment.showSnack
import org.v_points.ui_core.databinding.FavoritesFragmentBinding

class FavoritesFragment : BaseFragment(UiCoreLayout.favorites_fragment) {

    override val bottomMenuVisible: Boolean = false

    private lateinit var binding: FavoritesFragmentBinding

    private val favoritesComponent by lazy {
        FavoritesComponent.createFavoritesComponent()
    }

    override val viewModel by viewModels<FavoritesViewModel> {
        favoritesComponent.viewModelMapFactory()
    }

    private val delegateAdapter: FavoritesManager = FavoritesManager(
        imageClickListener = { imageId ->
            viewModel.onImageClicked(imageId)
        },
        youtubeClickListener = { link ->
            openYoutubeLink(link)
        },
        questionLikeClickListener = { headerUiItem ->
            viewModel.onQuestionLikeClicked(headerUiItem)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FavoritesFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.recyclerSolution.adapter = delegateAdapter
        binding.recyclerSolution.addItemDecoration(
            SolutionItemDecoration(
                paddingItems = resources.getDimension(
                    UiCoreDimens.solution_item_space_horizontal
                )
            )
        )

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.favoritesToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onObserveData() {
        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }

        viewModel.getLoadingState().observeFlow(this) { loading ->
            if (!loading) binding.swipeRefreshLayout.isRefreshing = false
            binding.progressContainer.isVisible = loading
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { uiEvent ->
            when (uiEvent) {
                is FavoritesViewModel.UiEvent.ShowSnack -> {
                    showSnack(
                        title = uiEvent.message,
                        backgroundColor = uiEvent.snackBarType.colorRes,
                        length = Snackbar.LENGTH_SHORT
                    )
                }

                is FavoritesViewModel.UiEvent.NavigatePhotoDetailed -> {
                    PhotoDetailsFragment.showDialog(childFragmentManager, uiEvent.imagePath)
                }
            }
        }
    }
}
