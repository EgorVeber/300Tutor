package org.threehundredtutor.presentation.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.fragment.getColorAttr
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_common.view_components.loadImageOriginal
import org.threehundredtutor.ui_core.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : DialogFragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding
    private var imagePath by BundleString(PHOTO_DETAILED_IMAGE_PATH, EMPTY_STRING)

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, -1)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            photoView.loadImageOriginal(imagePath)
            photoViewToolBar.setNavigationOnClickListener { dismiss() }
        }
    }

    override fun onResume() {
        super.onResume()
        requireDialog().window?.apply {
            statusBarColor = getColorAttr(UiCoreAttr.defaultBlack, false)
            navigationBarColor = getColorAttr(UiCoreAttr.defaultBlack, false)
        }
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
