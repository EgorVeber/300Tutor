package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.threehundredtutor.R
import org.threehundredtutor.databinding.RegistrationFragmentLayoutBinding
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment(R.layout.registration_fragment_layout) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RegistrationFragmentLayoutBinding.bind(view)
        binding.buttonRegister.setOnClickListener {
            viewModel.register(
                email = binding.etEmail.text.toString(),
                name = binding.etName.text.toString(),
                surname = binding.etSurname.text.toString(),
                patronymic = binding.etPatronymic.text.toString(),
                phoneNumber = binding.etPhone.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        observeData()

        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }

    private fun observeData() {
        //TODO завести observeWithLifecycle
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getRegistrationState().collect { registeredUser ->
                    if (registeredUser.succeded) {
                        val toast = Toast.makeText(context, "User registered", Toast.LENGTH_LONG)
                        toast.show()
                        findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getErrorState().collect { errorText ->
                    val toast =
                        Toast.makeText(context, errorText, Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        }
    }
}
