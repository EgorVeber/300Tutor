package org.threehundredtutor.presentation.solution

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.PHOTO_DETAILED_KEY
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.common.extentions.showSnackbar
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.getUrlYoutube
import org.threehundredtutor.databinding.SolutionFragmentBinding
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.presentation.common.ActionDialogFragment
import org.threehundredtutor.presentation.common.LoadingDialog
import org.threehundredtutor.presentation.solution.adapter.SolutionManager

class SolutionFragment : BaseFragment(R.layout.solution_fragment) {

    private lateinit var binding: SolutionFragmentBinding

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
            viewModel.onDetailedAnswerValidationClicked(
                answerValidationItemUiModel = answerValidationItemUiModel, inputPoint = inputPoint
            )
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SolutionFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
        binding.recyclerSolution.adapter = delegateAdapter
    }

    @SuppressLint("ShowToast")
    override fun onObserveData() {
        showSnackbar(
            title = "Вопрос #2 по предмету Математика ЕГЭ",
            length = Snackbar.LENGTH_INDEFINITE,
            description = "Решение теста началосьт.",
            buttonText = "asdasd",
            buttonClick = {}
        )

        viewModel.getTestInfoStateFlow().observeFlow(this) { testSolutionGeneralModel ->
            testSolutionGeneralModel?.let {
                binding.testTitle.text = it.nameTest
            }
        }

        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }

        viewModel.getLoadingStateFlow().observeFlow(this) { loading ->
            showLoadingDialog(loading)
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SolutionViewModel.UiEvent.ShowMessage -> showMessage(state.message)
                is SolutionViewModel.UiEvent.OpenYoutube -> showActionDialogOpenYoutube(state.link)
                is SolutionViewModel.UiEvent.NavigatePhotoDetailed -> navigatePhotoDetailed(state.imageId)
                is SolutionViewModel.UiEvent.ScrollToQuestion -> {}
                is SolutionViewModel.UiEvent.ShowSnack -> {}
                SolutionViewModel.UiEvent.ErrorSolution -> {}
            }
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


