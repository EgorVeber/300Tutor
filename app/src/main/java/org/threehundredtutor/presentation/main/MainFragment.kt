package org.threehundredtutor.presentation.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.main.MainComponent
import org.threehundredtutor.presentation.account.AccountFragment
import org.threehundredtutor.presentation.course.WebViewFragment
import org.threehundredtutor.presentation.main.activate_key_dialog.ActivateKeyDialogFragment
import org.threehundredtutor.presentation.main.adapter.MainManager
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_core.SnackBarType
import org.threehundredtutor.ui_core.databinding.MainFragmentBinding


class MainFragment : BaseFragment(UiCoreLayout.main_fragment) {

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
        activateKeyClickListener = {
            viewModel.onActivateKeyClicked()
        },
        tickUpClickListener = {
            viewModel.onTickUpClicked()
        },
        courseUiModelClickListener = { courseUiModel ->
            viewModel.onCourseClicked(courseUiModel)
        },
        courseProgressUiModelClickListener = { courseProgressUiModel ->
            viewModel.onCourseProgressClicked(courseProgressUiModel)
        },
        extraButtonClickListener = { link -> },
        courseLottieUiItemClickListener = {
            viewModel.onCourseLottieClicked()
        },
        telegramUiItemClickListener = {
            viewModel.onTelegramClicked()
        },
        telegramOpenUiItemClickListener = {
            viewModel.onOpenTelegramClicked()
        }
    )


    private fun setResultKeyListener() {
        childFragmentManager.setFragmentResultListener(
            ACTIVATE_KEY_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val message = bundle.getString(ACTIVATE_KEY_DATA) ?: ""
            if (message.isNotEmpty()) {
                showSnack(
                    title = message,
                    backgroundColor = SnackBarType.SUCCESS.colorRes
                )
            }
            viewModel.onSuccessActivateKey()
        }
    }

    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = MainFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.mainRecycler.adapter = delegateAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
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
                    showSnack(
                        title = state.message,
                        backgroundColor = state.snackBarType.colorRes,
                    )
                }

                is MainViewModel.UiEvent.NavigateWebScreen -> {
                    openSite(info = state)
                }

                MainViewModel.UiEvent.OpenActivateKeyDialog -> {
                    setResultKeyListener()
                    ActivateKeyDialogFragment.showDialog(childFragmentManager)
                }

                is MainViewModel.UiEvent.CopyLinkKeyboard -> {
                    copyClipBoard(link = state.link)
                    showSnack(
                        title = getString(UiCoreStrings.main_telegram_link),
                        description = getString(UiCoreStrings.main_telegram_copy_description),
                        backgroundColor = SnackBarType.SUCCESS.colorRes
                    )
                }

                is MainViewModel.UiEvent.OpenTelegram -> {
                    openTelegram(state.telegramBotUrl, state.telegramBotName)
                }
            }
        }

        viewModel.getLoadingState().observeFlow(this) { loading ->
            binding.progressContainer.isVisible = loading
            if (!loading) binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun openSite(info: MainViewModel.UiEvent.NavigateWebScreen) {
        navigate(R.id.action_mainFragment_to_webViewFragment, bundle = Bundle().apply {
            putString(WebViewFragment.LINK_KEY, info.urlAuthentication)
            putString(WebViewFragment.SITE_URL_KEY, info.siteUrl)
            putString(WebViewFragment.SCHOOL_KEY, info.schoolName)
        })
    }

    private fun openTelegram(telegramBotUrl: String, telegramBotName: String) {
        try {
            val uri = Uri.parse(
                AccountFragment.TELEGRAM_LINK.replace(
                    AccountFragment.TELEGRAM_BOT_SUB_STRING,
                    telegramBotName
                )
            )
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(telegramBotUrl)))
            } catch (e: Exception) {
                showMessage(getString(UiCoreStrings.unknown_error_message))
            }
        }
    }

    private fun copyClipBoard(link: String) {
        val clipboard = context?.getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager
        val clip = ClipData.newPlainText("MainFragment.TelegramItem.Clicked", link)
        clipboard!!.setPrimaryClip(clip)
    }

    companion object {
        const val ACTIVATE_KEY_KEY = "ACTIVATE_KEY_KEY"
        const val ACTIVATE_KEY_DATA = "ACTIVATE_KEY_DATA"

        const val SUBJECT_DETAILED_KEY_ID = "SUBJECT_DETAILED_KEY_ID"
        const val SUBJECT_DETAILED_KEY_NAME = "SUBJECT_DETAILED_KEY_NAME"
    }
}
