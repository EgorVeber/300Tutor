package org.threehundredtutor.presentation.solution

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.UiCoreStyle
import org.threehundredtutor.ui_common.fragment.applyWindowColor
import org.threehundredtutor.ui_common.fragment.dropWindowColor
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_common.view_components.loadImageOriginal
import org.threehundredtutor.ui_core.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : DialogFragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding
    private var imagePath by BundleString(PHOTO_DETAILED_IMAGE_PATH, EMPTY_STRING)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentPhotoDetailsBinding.inflate(layoutInflater)
        applyWindowColor(UiCoreAttr.defaultBlack)
        val dialog = Dialog(requireContext(), UiCoreStyle.PhotoDialogStyle)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            photoView.loadImageOriginal(imagePath)
            photoViewToolBar.setNavigationOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dropWindowColor()
    }

    companion object {
        const val PHOTO_DETAILED_IMAGE_PATH = "PHOTO_DETAILED_IMAGE_PATH"
        private const val TAG = "PhotoDetailsFragment"
        fun showDialog(
            fragmentManager: FragmentManager,
            imagePath: String,
        ) {
            if (fragmentManager.findFragmentByTag(TAG) == null) {
                PhotoDetailsFragment().apply {
                    this.imagePath = imagePath
                }.show(fragmentManager, TAG)
            }
        }
    }
}
