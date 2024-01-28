package org.threehundredtutor.presentation.common

import android.app.Dialog
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.threehundredtutor.R
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.databinding.DialogFragmentBinding

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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentBinding.inflate(layoutInflater)
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle).setView(
            binding.root
        ).create()
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            dialogTitle.text = title.ifEmpty { getString(R.string.attention) }
            dialogDescription.text = message
            positiveButton.text = positiveText.ifEmpty { getString(R.string.ok) }
            negativeButton.text = negativeText.ifEmpty { getString(R.string.cancel) }
            neutralButton.text = neutralText
            positiveButton.setOnClickListener {
                onPositiveClick?.invoke()
                dismiss()
            }
            negativeButton.setOnClickListener {
                onNegativeClick?.invoke()
                dismiss()
            }
            neutralText.ifEmpty {
                neutralButton.isVisible = false
                neutralView.isVisible = false
            }
            neutralButton.setOnClickListener {
                onNeutralClick?.invoke()
                dismiss()
            }
        }
        isCancelable = false
    }

    companion object {
        private const val TAG = "ActionDialogFragment"
        fun showDialog(
            fragmentManager: FragmentManager,
            title: String? = EMPTY_STRING,
            message: String,
            positiveText: String? = EMPTY_STRING,
            negativeText: String? = EMPTY_STRING,
            neutralText: String? = EMPTY_STRING,
            onPositiveClick: (() -> Unit)? = null,
            onNegativeClick: (() -> Unit)? = null,
            onNeutralClick: (() -> Unit)? = null
        ) {
            if (fragmentManager.findFragmentByTag(TAG) == null) {
                ActionDialogFragment().apply {
                    this.title = title ?: EMPTY_STRING
                    this.message = message.ifEmpty { EMPTY_STRING }
                    this.positiveText = positiveText ?: EMPTY_STRING
                    this.negativeText = negativeText ?: EMPTY_STRING
                    this.neutralText = neutralText ?: EMPTY_STRING
                    this.onPositiveClick = onPositiveClick
                    this.onNegativeClick = onNegativeClick
                    this.onNeutralClick = onNeutralClick
                }.show(fragmentManager, TAG)
            }
        }
    }
}