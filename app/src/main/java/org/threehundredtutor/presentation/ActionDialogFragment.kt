package org.threehundredtutor.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.threehundredtutor.R
import org.threehundredtutor.databinding.DialogFragmentBinding

class ActionDialogFragment : DialogFragment() {

    private var title: String? = null
    private var message: String? = null
    private var positiveText: String? = null
    private var negativeText: String? = null

    private var onPositiveClick: (() -> Unit)? = null
    private var onNegativeClick: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        val inflater = requireActivity().layoutInflater
        val binding = DialogFragmentBinding.inflate(layoutInflater)
        val view = binding.root

        val builder = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ThemeOverlay_App_MaterialAlertDialog
        ).setView(view)

        with(binding) {
            dialogTitle.text = title
            dialogDescription.text = message

            positiveButton.apply {
                text = positiveText
                setOnClickListener {
                    onPositiveClick?.invoke()
                    dismiss()
                }
            }

            negativeButton.apply {
                text = negativeText
                setOnClickListener {
                    onNegativeClick?.invoke()
                    dismiss()
                }
            }
        }
        return builder.create()
    }

    companion object {
        private const val TAG = "MyDialogFragment"
        fun showDialog(
            fragmentManager: FragmentManager,
            title: String,
            message: String,
            positiveText: String,
            negativeText: String,
            onPositiveClick: () -> Unit,
            onNegativeClick: () -> Unit
        ) {
            val dialogFragment = ActionDialogFragment()
            dialogFragment.title = title
            dialogFragment.message = message
            dialogFragment.positiveText = positiveText
            dialogFragment.negativeText = negativeText

            dialogFragment.onPositiveClick = onPositiveClick
            dialogFragment.onNegativeClick = onNegativeClick

            dialogFragment.show(fragmentManager, TAG)
        }
    }
}