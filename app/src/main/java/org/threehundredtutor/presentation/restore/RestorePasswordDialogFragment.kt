package org.threehundredtutor.presentation.restore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.threehundredtutor.core.UiCoreStyle
import org.threehundredtutor.di.restore_password.RestorePasswordComponent
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.launchDelay
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_core.databinding.RestorePasswordDialogBinding

// TODO Поправить макет чтоб клавитура была ниже кнопки связать кнопку на клаве с отправкой
class RestorePasswordDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: RestorePasswordDialogBinding

    override fun getTheme() = UiCoreStyle.AppBottomSheetDialogTheme

    private val restorePasswordComponent by lazy {
        RestorePasswordComponent.createRestorePasswordComponent()
    }

    val viewModel by viewModels<RestorePasswordViewModel> {
        restorePasswordComponent.viewModelMapFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RestorePasswordDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
        onObserveData()
    }

    private fun onInitView() {
        binding.sendEmailButton.setOnClickListener {
            viewModel.onSendButtonClicked(binding.emailEditText.text.toString())
        }
    }

    private fun onObserveData() {
        viewModel.getMessageStream().observeFlow(this) { message ->
            showSnack(title = message.first, backgroundColor = message.second.colorRes)
        }
        viewModel.getDismissAction().observeFlow(this) {
            launchDelay(DELAY_DISMISS) {
                dismiss()
            }
        }
    }

    companion object {
        const val DELAY_DISMISS = 2000L
        private val SolutionResultDialogFragmentTag =
            RestorePasswordDialogFragment::class.java.simpleName

        fun showDialog(
            childFragmentManager: FragmentManager
        ) {
            if (childFragmentManager.findFragmentByTag(SolutionResultDialogFragmentTag) == null) {
                RestorePasswordDialogFragment().apply {
                    show(childFragmentManager, SolutionResultDialogFragmentTag)
                }
            }
        }
    }
}