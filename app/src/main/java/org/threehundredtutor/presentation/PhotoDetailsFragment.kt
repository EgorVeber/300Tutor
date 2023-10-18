package org.threehundredtutor.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.appbar.MaterialToolbar
import org.threehundredtutor.R
import org.threehundredtutor.common.loadServer

class PhotoDetailsFragment : Fragment() {

    private val args: PhotoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            org.threehundredtutor.R.layout.fragment_photo_details,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoView = view.findViewById(org.threehundredtutor.R.id.photoView) as PhotoView
        val photoId = args.image
        photoView.loadServer(photoId)
        requireActivity().window.statusBarColor = Color.BLACK
        requireActivity().window.navigationBarColor = Color.BLACK
        val toolbar = view.findViewById<MaterialToolbar>(R.id.photoViewToolBar)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_photoDetailsFragment_to_solutionFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.statusBarColor = Color.alpha(0)
        requireActivity().window.navigationBarColor = Color.alpha(0)
    }
}