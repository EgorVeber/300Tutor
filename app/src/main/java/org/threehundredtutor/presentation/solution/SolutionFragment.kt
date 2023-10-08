package org.threehundredtutor.presentation.solution

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.videoId
import org.threehundredtutor.databinding.SolutionFragmentBinding
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.presentation.LoadingDialog
import org.threehundredtutor.presentation.solution.adapter.SolutionManager

class SolutionFragment : BaseFragment(R.layout.solution_fragment) {

    private val delegateAdapter: SolutionManager = SolutionManager(
        imageClick = { Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show() },
        answerWithErrorsClick = { questionAnswerWithErrorsUiModel, answer ->
            viewModel.answerWithErrorsClicked(
                questionAnswerWithErrorsUiModel = questionAnswerWithErrorsUiModel,
                answer = answer
            )
        },
        rightAnswerWithErrorsClick = { rightAnswerModel, answer ->
            viewModel.rightAnswerClicked(
                rightAnswerModel,
                answer
            )
        },
        detailedAnswerClick = { questionDetailedAnswerUiModel, answer ->
            viewModel.detailedAnswerClicked(
                questionAnswerWithErrorsUiModel = questionDetailedAnswerUiModel,
                answer = answer
            )
        },
        selectRightAnswerOrAnswersClick = { selectRightAnswerOrAnswersUiModel ->
            viewModel.selectRightAnswerOrAnswersClick(selectRightAnswerOrAnswersUiModel)
        },
        itemChecked = { answerText, checked, questionId ->
            viewModel.itemChecked(answerText, checked, questionId)
        },
        youtubeClick = { link ->
            viewModel.openYoutube(link)
            //TODO TutorAndroid-21  Добавить поддержку youtube в решении теста.
            openYoutubeLink(link)
        }
    )

    private val solutionComponent by lazy {
        SolutionComponent.createSolutionComponent()
    }

    override val viewModel by viewModels<SolutionViewModel> {
        solutionComponent.viewModelMapFactory()
    }

    private lateinit var binding: SolutionFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SolutionFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
        binding.recyclerSolution.adapter = delegateAdapter
        binding.recyclerSolution.setHasFixedSize(true)// TODO ПРоверить
    }

    override fun onObserveData() {
        viewModel.getUiItemStateFlow().observeFlow(this) { items ->
            delegateAdapter.items = items
        }
        viewModel.getLoadingStateFlow().observeFlow(this) { loading ->
            showLoadingDialog(loading)
        }
    }

    private fun showLoadingDialog(loading: Boolean) {
        if (loading) {
            LoadingDialog.show(requireActivity().supportFragmentManager)
        } else {
            LoadingDialog.close(requireActivity().supportFragmentManager)
        }
    }

    private fun openYoutubeLink(link: String) {
        // TODO TutorAndroid-21
        val id  = link.videoId()
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id))
        val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }
    }
}


