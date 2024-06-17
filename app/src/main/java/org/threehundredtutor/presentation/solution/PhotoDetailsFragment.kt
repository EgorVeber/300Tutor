package org.threehundredtutor.presentation.solution

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.fragment.applyWindowColor
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.fragment.dropWindowColor
import org.threehundredtutor.ui_common.view_components.loadImageOriginal
import org.threehundredtutor.ui_core.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : BaseFragment(UiCoreLayout.fragment_photo_details) {
    private lateinit var binding: FragmentPhotoDetailsBinding

    override val viewModel: BaseViewModel by viewModels()
    override val bottomMenuVisible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPhotoDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        applyWindowColor(UiCoreAttr.defaultBlack)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        val imagePath = arguments?.getString(PHOTO_DETAILED_IMAGE_PATH) ?: EMPTY_STRING
        binding.photoView.loadImageOriginal(imagePath)
        binding.photoViewToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dropWindowColor()
    }

    companion object {
        const val PHOTO_DETAILED_IMAGE_PATH = "PHOTO_DETAILED_IMAGE_PATH"
    }
}