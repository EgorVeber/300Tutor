package org.threehundredtutor.presentation.solution

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.videoId
import org.threehundredtutor.databinding.SolutionFragmentBinding
import org.threehundredtutor.di.solution.SolutionComponent
import org.threehundredtutor.presentation.LoadingDialog
import org.threehundredtutor.presentation.solution.adapter.SolutionManager

class SolutionFragment : BaseFragment(R.layout.solution_fragment) {

    private val bundle = Bundle()

    private val delegateAdapter: SolutionManager = SolutionManager(
        imageClick = {
            bundle.putString("MyArg", it)
            findNavController().navigate(
                R.id.action_solutionFragment_to_photoDetailsFragment, bundle
            )
        },
        answerWithErrorsClick = { questionAnswerWithErrorsUiModel, answer ->
            viewModel.answerWithErrorsClicked(
                answerWithErrorsUiModel = questionAnswerWithErrorsUiModel,
                answer = answer
            )
        },
        checkButtonAnswerClick = { rightAnswerModel, answer ->
            viewModel.rightAnswerClicked(
                rightAnswerModel,
                answer
            )
        },
        detailedAnswerClick = { questionDetailedAnswerUiModel, answer ->
            viewModel.detailedAnswerClicked(
                detailedAnswerUiModel = questionDetailedAnswerUiModel,
                answer = answer
            )
        },
        youtubeClick = { link ->
            viewModel.openYoutube(link)
            //TODO TutorAndroid-21  Добавить поддержку youtube в решении теста.
            openYoutubeLink(link)
        },
        itemChecked = { qId, text, cheked ->
            viewModel.itemChecked(qId, text, cheked)
        },
        checkButtonSelectAnswerClick = { questionId ->
            viewModel.checkButtonClicked(questionId)
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
        val id = link.videoId()
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


