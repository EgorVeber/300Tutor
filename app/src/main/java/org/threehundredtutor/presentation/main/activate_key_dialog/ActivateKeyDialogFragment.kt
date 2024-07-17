package org.threehundredtutor.presentation.main.activate_key_dialog

import android.os.Bundle
import android.text.InputFilter
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.threehundredtutor.core.UiCoreStyle
import org.threehundredtutor.di.main.ActivateKeyComponent
import org.threehundredtutor.presentation.main.MainFragment.Companion.ACTIVATE_KEY_DATA
import org.threehundredtutor.presentation.main.MainFragment.Companion.ACTIVATE_KEY_KEY
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_core.SnackBarType
import org.threehundredtutor.ui_core.databinding.ActivateKeyDialogBinding

// TODO Поправить макет чтоб клавитура была ниже кнопки связать кнопку на клаве с отправкой
class ActivateKeyDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: ActivateKeyDialogBinding

    override fun getTheme() = UiCoreStyle.AppBottomSheetDialogTheme

    private val activateKeyComponent by lazy {
        ActivateKeyComponent.createActivateKeyComponent()
    }

    val viewModel by viewModels<ActivateKeyViewModel> {
        activateKeyComponent.viewModelMapFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivateKeyDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
        onObserveData()
    }

    private fun onInitView() {
        binding.activateCourse.setOnClickListener {
            viewModel.onActivateKeyClicked(binding.activateKetEdt.text.toString())
        }

        binding.activateKetEdt.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                val key = binding.activateKetEdt.text.toString().trim()
                if (key.isNotEmpty()) {
                    viewModel.onActivateKeyClicked(key)
                    false
                } else {
                    true
                }
            } else {
                true
            }
        }
        binding.activateKetEdt.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            source.toString().filterNot { it.isWhitespace() }
        })
    }

    private fun onObserveData() {
        viewModel.getLoadingState().observeFlow(this) { loading ->
            if (loading) {
                LoadingDialog.show(requireActivity().supportFragmentManager)
            } else {
                LoadingDialog.close(requireActivity().supportFragmentManager)
            }
        }
        viewModel.getUiEventStateFlow().observeFlow(this) { uiEvent ->
            when (uiEvent) {
                is ActivateKeyViewModel.UiEvent.SendResultListener -> {
                    if (uiEvent.success) {
                        setFragmentResult(
                            ACTIVATE_KEY_KEY,
                            bundleOf(ACTIVATE_KEY_DATA to uiEvent.message)
                        )
                        dismiss()
                    } else {
                        showSnack(
                            title = uiEvent.message,
                            backgroundColor = SnackBarType.ERROR.colorRes
                        )
                    }
                }
            }
        }
    }

    companion object {
        private val activateKeyDialogFragment =
            ActivateKeyDialogFragment::class.java.simpleName

        fun showDialog(
            childFragmentManager: FragmentManager
        ) {
            if (childFragmentManager.findFragmentByTag(activateKeyDialogFragment) == null) {
                ActivateKeyDialogFragment().apply {
                    show(childFragmentManager, activateKeyDialogFragment)
                }
            }
        }
    }
}