package org.threehundredtutor.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.PHOTO_DETAILED_KEY
import org.threehundredtutor.common.loadServerOriginal
import org.threehundredtutor.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment :
    BaseFragment(R.layout.fragment_photo_details) {

    override val viewModel: BaseViewModel by viewModels()
    private lateinit var binding: FragmentPhotoDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPhotoDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
        val photoView = binding.photoView
        photoView.loadServerOriginal(arguments?.getString(PHOTO_DETAILED_KEY).toString())
        requireActivity().window.statusBarColor = Color.BLACK
        requireActivity().window.navigationBarColor = Color.BLACK
        val toolbar = binding.photoViewToolBar
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.statusBarColor = Color.alpha(0)
        requireActivity().window.navigationBarColor = Color.WHITE
    }
}