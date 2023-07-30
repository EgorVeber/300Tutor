package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.RegistrationFragmentLayoutBinding
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel

class RegistrationFragment : BaseFragment(R.layout.registration_fragment_layout) {

    override val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        viewModel.getRegistrationState().observeFlow(this) { registeredUser ->
            if (registeredUser.succeded) {
                showMessage(getString(R.string.register_success_message))
                findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
            }
        }
        viewModel.getResultNotSuccededFlow().observeFlow(this) { errorText ->
            showMessage(errorText)
        }
    }
}
