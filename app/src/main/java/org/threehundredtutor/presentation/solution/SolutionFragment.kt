package org.threehundredtutor.presentation.solution

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreDimens
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.domain.solution.models.test_model.HtmlPageTestType
import org.threehundredtutor.presentation.solution.PhotoDetailsFragment.Companion.PHOTO_DETAILED_IMAGE_PATH
import org.threehundredtutor.presentation.solution.adapter.SolutionItemDecoration
import org.threehundredtutor.presentation.solution.adapter.SolutionManager
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.ActionDialogFragment
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.openYoutubeLink
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_common.util.getUrlYoutube
import org.threehundredtutor.ui_common.util_class.BundleSerializable
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.SolutionFragmentBinding


class SolutionFragment : BaseFragment(UiCoreLayout.solution_fragment) {

    private lateinit var binding: SolutionFragmentBinding

    override val bottomMenuVisible: Boolean = false

    override var customHandlerBackStack: Boolean = true

    private val solutionId by BundleString(SOLUTION_SOLUTION_ID_KEY, EMPTY_STRING)
    private val testId by BundleString(SOLUTION_TEST_ID_KEY, EMPTY_STRING)
    private val directoryTestId by BundleString(SOLUTION_HTML_PAGE_DIRECTORY_ID_KEY, EMPTY_STRING)
    private val workSpaceId by BundleString(SOLUTION_HTML_PAGE_WORKSPACE_ID_KEY, EMPTY_STRING)
    private val htmlPageTestType by BundleSerializable(
        SOLUTION_HTML_PAGE_TEST_TYPE_ID_KEY,
        HtmlPageTestType.DEFAULT_EMPTY
    )

    private val solutionComponent by lazy {
        SolutionComponent.createSolutionComponent(
            SolutionParamsDaggerModel(
                testId = testId,
                solutionId = solutionId,
                directoryTestId = directoryTestId,
                workSpaceId = workSpaceId,
                htmlPageTestType = htmlPageTestType
            )
        )
    }

    override val viewModel by viewModels<SolutionViewModel> {
        solutionComponent.viewModelMapFactory()
    }

    private val delegateAdapter: SolutionManager = SolutionManager(
        imageClickListener = { imageId ->
            viewModel.onImageClicked(imageId)
        },
        youtubeClickListener = { link -> openYoutubeLink(link) },
        selectRightAnswerClickListener = { questionId, answer, checked ->
            viewModel.onSelectRightAnswerCheckedChange(
                questionId = questionId,
                answerText = answer,
                checked = checked
            )
        },
        selectRightAnswerCheckButtonClickListener = { selectRightAnswerCheckButtonUiItem ->
            viewModel.onSelectRightAnswerCheckButtonClicked(selectRightAnswerCheckButtonUiItem)
        },
        rightAnswerClickListener = { rightAnswerUiModel, inputAnswer ->
            viewModel.onRightAnswerClicked(
                rightAnswerUiModel = rightAnswerUiModel,
                inputAnswer = inputAnswer
            )
        },
        rightAnswerTextChangedListener = { questionId, inputAnswer ->
            viewModel.onRightAnswerTextChanged(questionId, inputAnswer)
        },
        answerWithErrorsClickListener = { questionAnswerWithErrorsUiModel, answer ->
            viewModel.onAnswerWithErrorClicked(
                answerWithErrorsUiModel = questionAnswerWithErrorsUiModel, answer = answer
            )
        }, answerWithErrorsTextChangedListener = { questionId, answer ->
            viewModel.onAnswerWithErrorsTextChanged(questionId, answer)
        },
        detailedAnswerClickListener = { detailedAnswerInputUiItem, inputAnswer ->
            viewModel.onDetailedAnswerClicked(
                detailedAnswerInputUiItem = detailedAnswerInputUiItem, inputAnswer = inputAnswer
            )
        },
        detailedAnswerValidationClickListener = { answerValidationItemUiModel, inputPoint ->
            viewModel.onDetailedAnswerValidationSaveClicked(
                answerValidationItemUiModel = answerValidationItemUiModel, inputPoint = inputPoint
            )
        },
        deleteValidationClickListener = { detailedAnswerValidationUiItem ->
            viewModel.onDeleteValidationClicked(detailedAnswerValidationUiItem)
        },
        detailedAnswerTextChangedListener = { questionId, inputAnswer ->
            viewModel.onDetailedAnswerTextChanged(questionId, inputAnswer)
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
        findNavController().popBackStack()
        //viewModel.onBackClicked()
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        super.onInitView(savedInstanceState)

        binding.recyclerSolution.adapter = delegateAdapter
        binding.recyclerSolution.addItemDecoration(
            SolutionItemDecoration(
                paddingItems = resources.getDimension(
                    UiCoreDimens.solution_item_space_horizontal
                )
            )
        )

        binding.recyclerSolution.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layoutManager = (recyclerView.layoutManager as? LinearLayoutManager) ?: return
                val totalItemCount = layoutManager.itemCount
                val endHasBeenReached = layoutManager.findLastVisibleItemPosition() + 1 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    viewModel.onLastItemVisible()
                }
            }
        })

        binding.solutionToolBar.setNavigationOnClickListener { onBackPressed() }
        binding.finishButton.setOnClickListener { viewModel.onFinishTestClicked() }
        binding.showResultButton.setOnClickListener { viewModel.onResultTestClicked() }
        binding.solutionToolBar.setOnClickListener {
            showMessage(binding.solutionToolBar.title.toString())
        }
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
            binding.finishButton.isVisible = isVisible // TODO кнопка поднимается с клавиатурой
        }

        viewModel.getResultButtonState().observeFlow(this) { isVisible ->
            binding.showResultButton.isVisible = isVisible
        }

        viewModel.getTestResultState().observeFlow(this) { text ->
            //binding.solutionToolBar.subtitle = text
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {
                is SolutionViewModel.UiEvent.ShowMessage -> showMessage(state.message)
                is SolutionViewModel.UiEvent.OpenYoutube -> openYoutubeLink(state.link)
                is SolutionViewModel.UiEvent.NavigatePhotoDetailed -> navigatePhotoDetailed(
                    imagePath = state.imagePath,
                )

                is SolutionViewModel.UiEvent.ShowSnack -> showSnack(
                    title = state.message,
                    backgroundColor = state.snackBarType.colorRes,
                    length = Snackbar.LENGTH_SHORT
                )

                SolutionViewModel.UiEvent.ShowFinishDialog -> {
                    ActionDialogFragment.showDialog(
                        fragmentManager = childFragmentManager,
                        title = getString(UiCoreStrings.finish_test_title_dialog),
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
        viewModel.getShowResultDialogEventFlow().observeFlow(this) { testResult ->
            SolutionResultDialogFragment.showDialog(testResult, childFragmentManager)
        }

        viewModel.getErrorStateFlow().observeFlow(this) { visible ->
            binding.tvError.isVisible = visible
        }
    }

    private fun showLoadingDialog(loading: Boolean) {
        if (loading) {
            LoadingDialog.show(requireActivity().supportFragmentManager)
        } else {
            LoadingDialog.close(requireActivity().supportFragmentManager)
        }
    }

    private fun navigatePhotoDetailed(imagePath: String) {
        navigate(R.id.action_solutionFragment_to_photoDetailsFragment, Bundle().apply {
            putString(PHOTO_DETAILED_IMAGE_PATH, imagePath)
        })
    }

    companion object {
        const val SOLUTION_SOLUTION_ID_KEY = "SOLUTION_SOLUTION_ID_KEY"
        const val SOLUTION_TEST_ID_KEY = "SOLUTION_TEST_ID_KEY"
        const val SOLUTION_HTML_PAGE_DIRECTORY_ID_KEY = "SOLUTION_HTML_PAGE_DIRECTORY_ID_KEY"
        const val SOLUTION_HTML_PAGE_WORKSPACE_ID_KEY = "SOLUTION_HTML_PAGE_WORKSPACE_ID_KEY"
        const val SOLUTION_HTML_PAGE_TEST_TYPE_ID_KEY = "SOLUTION_HTML_PAGE_TEST_TYPE_ID_KEY"
    }
}