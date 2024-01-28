package org.threehundredtutor.presentation.common

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import org.threehundredtutor.R

class LoadingDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.LoadingDialogStyle)
        dialog.setContentView(R.layout.loading_dialog_layout)
        dialog.setCancelable(false)
        isCancelable = false
        return dialog
    }

    override fun onResume() {
        super.onResume()
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (retainInstance) dialog?.setDismissMessage(null)
    }

    companion object {
        private val loadingDialogTag = LoadingDialog::class.java.simpleName

        fun show(fragmentManager: FragmentManager?) {
            if (fragmentManager == null) return
            (fragmentManager.findFragmentByTag(loadingDialogTag) as? LoadingDialog)?.dismissAllowingStateLoss()
            LoadingDialog().apply {
                retainInstance = true
            }.show(fragmentManager, loadingDialogTag)
        }

        fun close(fragmentManager: FragmentManager?) {
            fragmentManager?.fragments?.filter { dialog ->
                dialog.tag == loadingDialogTag
            }?.forEach { dialogFramgent ->
                (dialogFramgent as? LoadingDialog)?.dismissAllowingStateLoss()
            }
        }
    }
}