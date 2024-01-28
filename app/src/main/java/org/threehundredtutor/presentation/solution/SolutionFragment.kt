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
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.common.extentions.showSnackbar
import org.threehundredtutor.common.getUrlYoutube
import org.threehundredtutor.common.utils.BundleString
import org.threehundredtutor.databinding.SolutionFragmentBinding
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.presentation.PhotoDetailsFragment.Companion.PHOTO_DETAILED_KEY
import org.threehundredtutor.presentation.common.ActionDialogFragment
import org.threehundredtutor.presentation.common.LoadingDialog
import org.threehundredtutor.presentation.home.HomeFragment.Companion.SUBJECT_TEST_KEY
import org.threehundredtutor.presentation.solution.adapter.SolutionManager
import org.threehundredtutor.presentation.solution_history.SolutionHistoryFragment.Companion.SOLUTION_TEST_KEY

class SolutionFragment : BaseFragment(R.layout.solution_fragment) {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SolutionFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        viewModel.onBackClicked(binding.errorImageView.isVisible)
    }

    private val subjectId by BundleString(SUBJECT_TEST_KEY, EMPTY_STRING)

    private val solutionId by BundleString(SOLUTION_TEST_KEY, EMPTY_STRING)

    override fun onInitView(savedInstanceState: Bundle?) {
        super.onInitView(savedInstanceState)
        binding.recyclerSolution.adapter = delegateAdapter
        binding.accountToolBar.setNavigationOnClickListener { onBackPressed() }
        binding.finishButton.setOnClickListener { viewModel.onFinishTestClicked() }
        binding.showResultButton.setOnClickListener { viewModel.onResultTestClicked() }
        binding.accountToolBar.setOnClickListener {
            showMessage(binding.accountToolBar.title.toString())
        }
        viewModel.onViewInitiated(subjectId, solutionId)
    }

    override fun onObserveData() {
        viewModel.getTestInfoStateFlow().observeFlow(this) { nameTest ->
            binding.accountToolBar.title = nameTest
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
                is SolutionViewModel.UiEvent.NavigatePhotoDetailed -> navigatePhotoDetailed(state.imageId)
                is SolutionViewModel.UiEvent.ShowSnack -> showSnackbar(
                    title = state.message,
                    backgroundColor = state.snackBarType.colorRes,
                    length = Snackbar.LENGTH_SHORT
                )

                is SolutionViewModel.UiEvent.ScrollToQuestion -> {}
                SolutionViewModel.UiEvent.ErrorSolution -> {}
                SolutionViewModel.UiEvent.ShowFinishDialog -> {
                    ActionDialogFragment.showDialog(
                        fragmentManager = childFragmentManager,
                        title = getString(R.string.finish_test_dialog),
                        message = getString(R.string.finish_test_message),
                        positiveText = getString(R.string.finish),
                        neutralText = getString(R.string.come_back_later),
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
            positiveText = getString(R.string.video_action),
            message = getString(R.string.open_youtube_message),
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

    private fun navigatePhotoDetailed(imageId: String) {
        navigate(R.id.action_solutionFragment_to_photoDetailsFragment, Bundle().apply {
            putString(PHOTO_DETAILED_KEY, imageId)
        })
    }
}


