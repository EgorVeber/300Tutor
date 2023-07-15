package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.databinding.RegistrationFragmentLayoutBinding

class RegistrationFragment : Fragment(R.layout.registration_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RegistrationFragmentLayoutBinding.bind(view)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }
}
