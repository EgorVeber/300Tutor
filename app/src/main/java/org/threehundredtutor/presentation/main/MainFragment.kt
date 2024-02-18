package org.threehundredtutor.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.SITE_URL
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showSnackbar
import org.threehundredtutor.databinding.MainFragmentBinding
import org.threehundredtutor.di.main.MainComponent
import org.threehundredtutor.presentation.common.ActionDialogFragment
import org.threehundredtutor.presentation.main.adapter.MainManager

class MainFragment : BaseFragment(R.layout.main_fragment) {

    private val mainComponent by lazy {
        MainComponent.createHomeComponent()
    }

    override var customHandlerBackStackWithDelay = true

    override val viewModel: MainViewModel by viewModels {
        mainComponent.viewModelMapFactory()
    }

    private val delegateAdapter: MainManager = MainManager(
        subjectClickListener = { subjectUiModel ->
            viewModel.onSubjectClicked(subjectUiModel)
        },
        activateKeyClickListener = { key ->
            viewModel.onActivateKeyClicked(key)
        },
        courseUiModelClickListener = { courseUiModel ->
            viewModel.onCourseClicked(courseUiModel)
        },
        courseProgressUiModelClickListener = { courseProgressUiModel ->
            viewModel.onCourseProgressClicked(courseProgressUiModel)
        },
        extraButtonClickListener = { link ->
            viewModel.onExtraButtonClicked(link)
        },
        courseLottieUiItemClickListener = {
            viewModel.onCourseLottieClicked()
        }
    )

    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = MainFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.mainRecycler.adapter = delegateAdapter
    }

    override fun onObserveData() {
        viewModel.getUiItemStateFlow().observeFlow(this) { subjectUiItems ->
            delegateAdapter.items = subjectUiItems
        }

        viewModel.getUiEventStateFlow().observeFlow(this) { state ->
            when (state) {

                is MainViewModel.UiEvent.NavigateToDetailedSubject -> {
                    navigate(R.id.action_mainFragment_to_subjectDetailedFragment,
                        Bundle().apply {
                            putString(SUBJECT_DETAILED_KEY_ID, state.subjectInfo.first)
                            putString(SUBJECT_DETAILED_KEY_NAME, state.subjectInfo.second)
                        })
                }

                is MainViewModel.UiEvent.ShowSnack -> {
                    showSnackbar(
                        title = state.message,
                        backgroundColor = state.snackBarType.colorRes,
                    )
                }

                is MainViewModel.UiEvent.ShowDialogOpenLink -> {
                    showActionDialogOpenLink(state.link)
                }

                is MainViewModel.UiEvent.OpenLink -> {
                    openSite(state.link)
                }
            }
        }

        viewModel.getLoadingState().observeFlow(this) { state ->
            binding.progressContainer.isVisible = state
        }
    }

    private fun showActionDialogOpenLink(link: String) {
        ActionDialogFragment.showDialog(
            fragmentManager = childFragmentManager,
            positiveText = getString(R.string.ok),
            title = getString(R.string.confirm_action),
            message = getString(R.string.open_link, link),
            onPositiveClick = {
                viewModel.onDialogOpenLinkPositiveClicked(link)
            },
        )
    }

    private fun openSite(link: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        } catch (e: Exception) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL)))
        }
    }

    companion object {
        const val SUBJECT_TEST_KEY = "SUBJECT_TEST_KEY" //TODO TutorAndroid-61
        const val SUBJECT_DETAILED_KEY_ID = "SUBJECT_DETAILED_KEY_ID"
        const val SUBJECT_DETAILED_KEY_NAME = "SUBJECT_DETAILED_KEY_NAME"
    }
}
