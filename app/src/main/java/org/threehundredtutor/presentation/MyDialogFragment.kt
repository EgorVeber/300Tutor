package org.threehundredtutor.presentation

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.threehundredtutor.R

class MyDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent)
    }

    private var title: String? = null
    private var message: String? = null
    private var positiveText: String? = null
    private var negativeText: String? = null

    private var onPositiveClick: (() -> Unit)? = null
    private var onNegativeClick: (() -> Unit)? = null

    fun showDialog(
        fragmentManager: FragmentManager,
        title: String,
        message: String,
        positiveText: String,
        negativeText: String,
        onPositiveClick: () -> Unit,
        onNegativeClick: () -> Unit
    ) {
        this.title = title
        this.message = message
        this.positiveText = positiveText
        this.negativeText = negativeText

        this.onPositiveClick = onPositiveClick
        this.onNegativeClick = onNegativeClick

        isCancelable = false
        show(fragmentManager, "MyDialogFragment")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment, null)
        return requireContext().let {
            val builder = MaterialAlertDialogBuilder(it)
            builder.setView(view)
            builder.apply {
                view.findViewById<TextView>(R.id.dialogTitle).text = title
                view.findViewById<TextView>(R.id.dialogDescription).text = message
                positiveText?.let {
                    view.findViewById<Button>(R.id.positiveButton).apply {
                        text = it
                        setOnClickListener {
                            onPositiveClick?.invoke()
                            dismiss()
                        }
                    }
                }
                negativeText?.let {
                    view.findViewById<Button>(R.id.negativeButton).apply {
                        text = it
                        setOnClickListener {
                            onNegativeClick?.invoke()
                            dismiss()
                        }
                    }
                }
            }
            builder.create()
        }
    }
}