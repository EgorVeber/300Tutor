package org.threehundredtutor.ui_common.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.UiCoreStrings
import org.threehundredtutor.ui_common.UiCoreStyle
import org.threehundredtutor.ui_core.databinding.DialogFragmentBinding

class ActionDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentBinding

    // TODO при смене темы текс и клили не сохраняется добавить конструктор наверное. Проиграть с большим текстом кнопок.
    private var title: String = EMPTY_STRING
    private var message: String = EMPTY_STRING
    private var positiveText: String = EMPTY_STRING
    private var negativeText: String = EMPTY_STRING
    private var neutralText: String = EMPTY_STRING
    private var onPositiveClick: (() -> Unit)? = null
    private var onNegativeClick: (() -> Unit)? = null
    private var onNeutralClick: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(KEY_TITLE, EMPTY_STRING)
            message = it.getString(KEY_MESSAGE, EMPTY_STRING)
            positiveText = it.getString(KEY_POSITIVE_TEXT, EMPTY_STRING)
            negativeText = it.getString(KEY_NEGATIVE_TEXT, EMPTY_STRING)
            neutralText = it.getString(KEY_NEUTRAL_TEXT, EMPTY_STRING)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentBinding.inflate(layoutInflater)
        return MaterialAlertDialogBuilder(requireContext(), UiCoreStyle.AlertDialogStyle).setView(
            binding.root
        ).create()
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            dialogTitle.text = title.ifEmpty { getString(UiCoreStrings.attention) }
            dialogDescription.text = message
            positiveButton.text = positiveText.ifEmpty { getString(UiCoreStrings.ok) }
            negativeButton.text = negativeText.ifEmpty { getString(UiCoreStrings.cancel) }
            neutralButton.text = neutralText
            positiveButton.setOnClickListener {
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to POSITIVE_BUTTON_CLICKED))
                onPositiveClick?.invoke()
                dismiss()
            }
            negativeButton.setOnClickListener {
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to NEGATIVE_BUTTON_CLICKED))
                onNegativeClick?.invoke()
                dismiss()
            }
            neutralText.ifEmpty {
                neutralButton.isVisible = false
                neutralView.isVisible = false
            }
            neutralButton.setOnClickListener {
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to NEUTRAL_BUTTON_CLICKED))
                onNeutralClick?.invoke()
                dismiss()
            }
        }
        isCancelable = false
    }

    companion object {
        private const val TAG = "ActionDialogFragment"
        private const val KEY_TITLE = "dialog_title"
        private const val KEY_MESSAGE = "dialog_message"
        private const val KEY_POSITIVE_TEXT = "dialog_positive_text"
        private const val KEY_NEGATIVE_TEXT = "dialog_negative_text"
        private const val KEY_NEUTRAL_TEXT = "dialog_neutral_text"
        const val REQUEST_KEY = "action_dialog_request_key"
        const val RESULT_KEY = "action_dialog_result_key"
        const val POSITIVE_BUTTON_CLICKED = "positive_button_clicked"
        const val NEGATIVE_BUTTON_CLICKED = "negative_button_clicked"
        const val NEUTRAL_BUTTON_CLICKED = "neutral_button_clicked"
        fun showDialog(
            fragmentManager: FragmentManager,
            title: String? = EMPTY_STRING,
            message: String,
            positiveText: String? = EMPTY_STRING,
            negativeText: String? = EMPTY_STRING,
            neutralText: String? = EMPTY_STRING,
        ) {
            if (fragmentManager.findFragmentByTag(TAG) == null) {
                ActionDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(KEY_TITLE, title)
                        putString(KEY_MESSAGE, message)
                        putString(KEY_POSITIVE_TEXT, positiveText)
                        putString(KEY_NEGATIVE_TEXT, negativeText)
                        putString(KEY_NEUTRAL_TEXT, neutralText)
                    }
                }
                    .show(fragmentManager, TAG)
            }
        }
    }
}
