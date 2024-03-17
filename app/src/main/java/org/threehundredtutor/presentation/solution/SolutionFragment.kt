package org.threehundredtutor.presentation.solution

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.presentation.solution.PhotoDetailsFragment.Companion.PHOTO_DETAILED_IMAGE_ID_KEY
import org.threehundredtutor.presentation.solution.PhotoDetailsFragment.Companion.PHOTO_DETAILED_STATIC_ORIGINAL_URL_KEY
import org.threehundredtutor.presentation.solution.adapter.SolutionManager
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.ActionDialogFragment
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_common.util.getUrlYoutube
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.SolutionFragmentBinding

class SolutionFragment : BaseFragment(UiCoreLayout.solution_fragment) {

    private lateinit var binding: SolutionFragmentBinding

    override val bottomMenuVisible: Boolean = false

    override var customHandlerBackStack: Boolean = true

    private val solutionComponent by lazy {
        SolutionComponent.createSolutionComponent()
    }

    override val viewModel by viewModels<SolutionViewModel> {
        solutionComponent.viewModelMapFactory()
    }

    private val delegateAdapter: SolutionManager = SolutionManager(
        imageClickListener = { imageId ->
            viewModel.onImageClicked(imageId)
        },
        youtubeClickListener = { link -> viewModel.onYoutubeClicked(link) },
        selectRightAnswerClickListener = { questionId, answer, checked ->
            viewModel.onCheckedChangeSelectRightAnswer(
                questionId = questionId,
                answerText = answer,
                checked = checked
            )
        },
        selectRightAnswerCheckButtonClickListener = { questionId ->
            viewModel.onSelectRightAnswerCheckButtonClicked(questionId)
        },
        rightAnswerClickListener = { rightAnswerUiModel, answer ->
            viewModel.onRightAnswerClicked(rightAnswerUiModel = rightAnswerUiModel, answer = answer)
        },
        answerWithErrorsClickListener = { questionAnswerWithErrorsUiModel, answer ->
            viewModel.onAnswerWithErrorClicked(
                answerWithErrorsUiModel = questionAnswerWithErrorsUiModel, answer = answer
            )
        },
        detailedAnswerClickListener = { questionDetailedAnswerUiModel, answer ->
            viewModel.onDetailedAnswerClicked(
                detailedAnswerUiItem = questionDetailedAnswerUiModel, answer = answer
            )
        },
        detailedAnswerValidationClickListener = { answerValidationItemUiModel, inputPoint ->
            viewModel.onDetailedAnswerValidationSaveClicked(
                answerValidationItemUiModel = answerValidationItemUiModel, inputPoint = inputPoint
            )
        },
        deleteValidationClickListener = { answerValidationItemUiModel ->
            viewModel.onDeleteValidationClicked(answerValidationItemUiModel)
        },
        detailedAnswerTextChangedListener = { item, text ->
            viewModel.onDetailedAnswerTextChanged(item, text)
        },
        questionLikeClickListener = { headerUiItem ->
            viewModel.onQuestionLikeClicked(headerUiItem)
        }
    )

    private val solutionId by BundleString(SOLUTION_SOLUTION_ID_KEY, EMPTY_STRING)
    private val testId by BundleString(SOLUTION_TEST_ID_KEY, EMPTY_STRING)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SolutionFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        viewModel.onBackClicked(binding.errorImageView.isVisible)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        super.onInitView(savedInstanceState)
        binding.recyclerSolution.adapter = delegateAdapter
        binding.solutionToolBar.setNavigationOnClickListener { onBackPressed() }
        binding.finishButton.setOnClickListener { viewModel.onFinishTestClicked() }
        binding.showResultButton.setOnClickListener { viewModel.onResultTestClicked() }
        binding.solutionToolBar.setOnClickListener {
            showMessage(binding.solutionToolBar.title.toString())
        }
        viewModel.onViewInitiated(testId, solutionId)
    }

    override fun onObserveData() {
        viewModel.getTestInfoStateFlow().observeFlow(this) { nameTest ->
            binding.solutionToolBar.title = nameTest
        }

        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }

        viewModel.getLoadingStateFlow().observeFlow(this) { loading ->
            showLoadingDialog(loading)
        }

        viewModel.getFinishButtonState().observeFlow(this) { isVisible ->
            binding.finishButton.isVisible = isVisible
        }

        viewModel.getResultButtonState().observeFlow(this) { isVisible ->
            binding.showResultButton.isVisible = isVisible
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SolutionViewModel.UiEvent.ShowMessage -> showMessage(state.message)
                is SolutionViewModel.UiEvent.OpenYoutube -> showActionDialogOpenYoutube(state.link)
                is SolutionViewModel.UiEvent.NavigatePhotoDetailed -> navigatePhotoDetailed(
                    imageId = state.imageId,
                    staticOriginalUrl = state.staticOriginalUrl
                )
                is SolutionViewModel.UiEvent.ShowSnack -> showSnack(
                    title = state.message,
                    backgroundColor = state.snackBarType.colorRes,
                    length = Snackbar.LENGTH_SHORT
                )

                is SolutionViewModel.UiEvent.ScrollToQuestion -> {}
                SolutionViewModel.UiEvent.ErrorSolution -> {}
                SolutionViewModel.UiEvent.ShowFinishDialog -> {
                    ActionDialogFragment.showDialog(
                        fragmentManager = childFragmentManager,
                        title = getString(UiCoreStrings.finish_test_dialog),
                        message = getString(UiCoreStrings.finish_test_message),
                        positiveText = getString(UiCoreStrings.finish),
                        neutralText = getString(UiCoreStrings.come_back_later),
                        onPositiveClick = {
                            viewModel.onFinishTestClicked()
                        },
                        onNeutralClick = {
                            findNavController().popBackStack()
                        }
                    )
                }

                SolutionViewModel.UiEvent.NavigateBack -> findNavController().popBackStack()
            }
        }
        viewModel.getResultTestStateFlow().observeFlow(this) { testResult ->
            SolutionResultDialogFragment.showDialog(testResult, childFragmentManager)
        }

        viewModel.getErrorStateFlow().observeFlow(this) { showError ->
            binding.errorImageView.isVisible = showError
            binding.rootContent.isVisible = !showError
        }
    }

    private fun showActionDialogOpenYoutube(link: String) {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            positiveText = getString(UiCoreStrings.video_action),
            message = getString(UiCoreStrings.open_youtube_message),
            onPositiveClick = { openYoutubeLink(link) },
        )
    }


    private fun showLoadingDialog(loading: Boolean) {
        if (loading) {
            LoadingDialog.show(requireActivity().supportFragmentManager)
        } else {
            LoadingDialog.close(requireActivity().supportFragmentManager)
        }
    }

    private fun openYoutubeLink(link: String) {
        val (appUri, browserUri) = link.getUrlYoutube()
        try {
            startActivity(Intent(Intent.ACTION_VIEW, appUri))
        } catch (ex: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, browserUri))
        }
    }

    private fun navigatePhotoDetailed(imageId: String, staticOriginalUrl: String) {
        navigate(R.id.action_solutionFragment_to_photoDetailsFragment, Bundle().apply {
            putString(PHOTO_DETAILED_IMAGE_ID_KEY, imageId)
            putString(PHOTO_DETAILED_STATIC_ORIGINAL_URL_KEY, staticOriginalUrl)
        })
    }

    companion object {
        const val SOLUTION_SOLUTION_ID_KEY = "SOLUTION_SOLUTION_ID_KEY"
        const val SOLUTION_TEST_ID_KEY = "SOLUTION_TEST_ID_KEY"
    }
}


