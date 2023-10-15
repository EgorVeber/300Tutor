package org.threehundredtutor.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.PHOTO_DETAILED_KEY
import org.threehundredtutor.common.loadServerOriginal
import org.threehundredtutor.common.viewcopmponents.applyWindowColor
import org.threehundredtutor.common.viewcopmponents.dropWindowColor
import org.threehundredtutor.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : BaseFragment(R.layout.fragment_photo_details) {
    private lateinit var binding: FragmentPhotoDetailsBinding

    override val viewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPhotoDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        applyWindowColor(R.attr.defaultBlack)
    }

    override fun onInitView() {
        val imageId = arguments?.getString(PHOTO_DETAILED_KEY) ?: EMPTY_STRING

        if (imageId.isNotEmpty()) binding.photoView.loadServerOriginal(imageId)
        else findNavController().popBackStack()

        binding.photoViewToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dropWindowColor()
    }
}