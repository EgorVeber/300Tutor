package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.RegistrationFragmentBinding
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel

class RegistrationFragment : BaseFragment(R.layout.registration_fragment)  {

    override val viewModel: RegistrationViewModel by viewModels()

    lateinit var binding: RegistrationFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = RegistrationFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        binding.buttonRegister.setOnClickListener {
            val passwordText = binding.etPassword.text.toString()
            if(passwordText.length < 6 || passwordText.contains("[0-9]".toRegex()).not()){
                showMessage(getString(R.string.password_wrong))
                return@setOnClickListener
            }
            if(binding.etEmail.text.toString().contains("@").not()){
                showMessage(getString(R.string.email_wrong))
                return@setOnClickListener
            }
            if(binding.etPhohe.text.toString().isEmpty()){
                showMessage(getString(R.string.phone_wrong))
                return@setOnClickListener
            }
            viewModel.register(
                email = binding.etEmail.text.toString(),
                name = binding.etName.text.toString(),
                surname = binding.etSurname.text.toString(),
                patronymic = binding.etPatronymic.text.toString(),
                phoneNumber = binding.etPhohe.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }


    override fun onObserveData() {
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
